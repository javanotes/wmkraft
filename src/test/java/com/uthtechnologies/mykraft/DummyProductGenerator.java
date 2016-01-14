/* ============================================================================
*
* FILE: DummyProductGenerator.java
*
* MODULE DESCRIPTION:
* See class description
*
* Copyright (C) 2015 
*
* All rights reserved
*
* ============================================================================
*/
package com.uthtechnologies.mykraft;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uthtechnologies.mykraft.dao.ProductCategoryRepo;
import com.uthtechnologies.mykraft.dao.ProductLineRepo;
import com.uthtechnologies.mykraft.dao.ProductRepo;
import com.uthtechnologies.mykraft.dao.ProductStockRepo;
import com.uthtechnologies.mykraft.dao.UserRepo;
import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductCategory;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineTag;
import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.Product;
import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductSKU;
import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductSpecification;
import com.uthtechnologies.mykraft.dao.entity.catalog.vendor.ProductStock;
import com.uthtechnologies.mykraft.dao.entity.util.AssociationHelper;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductCostingSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductDimensionSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XCommServer.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = false)
public class DummyProductGenerator {
  
  @Autowired
  private ProductCategoryRepo catgRepo;
  @Autowired
  private ProductLineRepo prodLineRepo;
  @Autowired
  private UserRepo userRepo;
  
  private ProductCategory catg;
  private ProductLine prodLine;
  
  final int catgN = 1, prodLineN = 1, prodN = 1;
    
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Test
  public void testCreateSingleUserProductCatalog()
  {
    try 
    {
      clear();
            
      User u = userRepo.findByUserName("admin");
      
      for(int i=0; i<catgN; i++)
      {
        
        catg = new ProductCategory();
        catg.setCategory("CATG-"+i);
        System.err.println("New category "+catg.getCategory());
        catg.setDescript(new DescriptionSupport(i+" category", "This is the "+i+"[th] category in the universe"));
        catg = catgRepo.saveAndFlush(catg);
        
        for(int j=0; j<prodLineN; j++)
        {
          
          prodLine = catg.newProductLine();
          prodLine.setCatgDispOrder(j);
          prodLine.setProdTypeLabel("PTYPE-"+i+j);
          System.err.println("New product line "+prodLine.getProdTypeLabel());
          prodLine = prodLineRepo.saveAndFlush(prodLine);
          
          ProductLineSpecification ps = prodLine.newProductSpecification();
          ps.setSpecCode("FORM");
          ps.setLabel("Form Factor");
          ps = prodLine.newProductSpecification();
          ps.setSpecCode("TYPE");
          ps.setLabel("Material Type");
          ps = prodLine.newProductSpecification();
          ps.setSpecCode("AGE");
          ps.setLabel("Material Ageing");
          
          prodLine = prodLineRepo.saveAndFlush(prodLine);
          
          ProductLineTag pt = prodLine.newProductTag();
          pt.setTag(prodLine.getProdTypeLabel() +"tag1");
          pt = prodLine.newProductTag();
          pt.setTag(prodLine.getProdTypeLabel() +"tag2");
          pt = prodLine.newProductTag();
          pt.setTag(prodLine.getProdTypeLabel() +"tag3");
          prodLine = prodLineRepo.saveAndFlush(prodLine);
          
          for(int k=0; k<prodN; k++)
          {
            Product vp = prodLine.newVendorProduct();
            vp.setActive(true);
            vp.setProdDispOrder(1);
            vp.setVendorProdLabel("Vendor Product - "+i+j+k);
            vp.setVendor(u);
                        
            for(ProductLineSpecification plSpec : prodLine.getSpecs())
            {
              ProductSpecification vSpec = vp.newSpecification(plSpec);
              switch (plSpec.getSpecCode()) {
              case "FORM":
                vSpec.setDescript(vp.getVendorProdLabel() + ": The form factor spec is defined here");
                break;
              case "TYPE":
                vSpec.setDescript(vp.getVendorProdLabel() + ": The material type spec is defined here");
                break;
              case "AGE":
                vSpec.setDescript(vp.getVendorProdLabel() + ": The material age spec is defined here");
                break;

              default:
                break;
              }
              
            }
            
            vp.setDescript(new DescriptionSupport(vp.getVendorProdLabel() + " Description", "This is an actual vendor product detailed description for the product"));
            
            vp = prodRepo.saveAndFlush(vp);
            vp = prodRepo.findOne(vp.getId());
            em.refresh(vp);
            
            ProductSKU sku = vp.newSKU("Size", "M");
            sku.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
            sku.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
            
            sku = vp.newSKU("Size", "L");
            sku.setDimension(new ProductDimensionSupport(4.0, 1.75, 0.85, 350.0));
            sku.setCosting(new ProductCostingSupport(12.5, 1500.00, 55.0, 115.5));
            
            sku = vp.newSKU("Size", "S");
            sku.setDimension(new ProductDimensionSupport(3.0, 0.75, 0.5, 150.0));
            sku.setCosting(new ProductCostingSupport(12.5, 500.00, 55.0, 100.0));
            
            vp = prodRepo.saveAndFlush(vp);
            
            
            for(ProductSKU s : vp.getSku())
            {
              ProductStock stk = new ProductStock();
              stk.setProduct(s);
              stk.setQtyInStock(100);
              prodStkRepo.save(stk);
            }
                        
          }
          
          //txnMgr.commit(txn);
          em.flush();
          em.clear();
          System.err.println("-- Flushed transaction --");
          
        }
        
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }
  @Autowired
  private EntityManager em;
  @Autowired
  private ProductRepo prodRepo;
  @Autowired
  private ProductStockRepo prodStkRepo;
  @Autowired
  private AssociationHelper assocHelper;
  
  @Before
  public void clear()
  {
    List<ProductCategory> c = catgRepo.findAll();
    if(c != null && !c.isEmpty())
    {
      for(ProductCategory pc : c)
      {
        assocHelper.removeForeignKeyAssociations(pc);
        pc = catgRepo.saveAndFlush(pc);
        catgRepo.delete(pc);
      }
    }
  }

}
