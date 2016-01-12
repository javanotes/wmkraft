/* ============================================================================
*
* FILE: CatalogDAOTest.java
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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uthtechnologies.mykraft.dao.CategoryRepo;
import com.uthtechnologies.mykraft.dao.ProductLineRepo;
import com.uthtechnologies.mykraft.dao.UserRepo;
import com.uthtechnologies.mykraft.dao.entity.auth.User;
import com.uthtechnologies.mykraft.dao.entity.catalog.Category;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductTag;
import com.uthtechnologies.mykraft.dao.entity.catalog.VendorProduct;
import com.uthtechnologies.mykraft.dao.entity.catalog.VendorProductSpecification;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductCostingSupport;
import com.uthtechnologies.mykraft.dao.entity.util.ProductDimensionSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
@WebAppConfiguration
public class CatalogDAOTest {

  private Category catg;
  private ProductLine prodLine;
  
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Test
  @Rollback(false)
  public void testCreateMultiProductCatalog()
  {
    try 
    {
      catg = new Category();
      catg.setCategory("PURSE");
      catg.setDescript(new DescriptionSupport("Wallets & Purses", "All items for wallets and purses"));
      catg = catgRepo.saveAndFlush(catg);
      
      prodLine = catg.newProductLine();
      prodLine.setCatgDispOrder(1);
      prodLine.setProdTypeLabel("JAIPUR CLUTCH");
      catg = catgRepo.saveAndFlush(catg);
      catg.getProducts().size();
      prodLine = catg.getProducts().iterator().next();
      
      Assert.assertNotNull("Product Line entity was not merged", prodLine.getId());
      prodLine = prodLineRepo.findOne(prodLine.getId());
      
      ProductLineSpecification ps = prodLine.newProductSpecification();
      ps.setSpecCode("FORM");
      ps.setLabel("Form Factor");
      ps = prodLine.newProductSpecification();
      ps.setSpecCode("TYPE");
      ps.setLabel("Type Factor");
      ps = prodLine.newProductSpecification();
      ps.setSpecCode("AGE");
      ps.setLabel("Age Factor");
      
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      Assert.assertEquals(3, prodLine.getSpecs().size());
      Assert.assertNotNull("ProductLineSpecification entity was not merged", prodLine.getSpecs().iterator().next().getId());
      
      ProductTag pt = prodLine.newProductTag();
      pt.setTag("wallet");
      pt = prodLine.newProductTag();
      pt.setTag("purse");
      pt = prodLine.newProductTag();
      pt.setTag("handbag");
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      Assert.assertEquals(3, prodLine.getTags().size());
      Assert.assertNotNull("ProductTag entity was not merged", prodLine.getTags().iterator().next().getId());
      User u = userRepo.findByUserName("admin");
      
      VendorProduct vp = prodLine.newVendorProduct();
      vp.setActive(true);
      vp.setProdDispOrder(1);
      vp.setVendor(u);
      vp.setVendorProdLabel("Camel Purse A111");
      
      Assert.assertFalse(prodLine.getSpecs().isEmpty());
      for(ProductLineSpecification plSpec : prodLine.getSpecs())
      {
        VendorProductSpecification vSpec = vp.newSpecification(plSpec);
        switch (plSpec.getSpecCode()) {
        case "FORM":
          vSpec.setDescript("The form factor spec is defined here");
          break;
        case "TYPE":
          vSpec.setDescript("The material type spec is defined here");
          break;
        case "AGE":
          vSpec.setDescript("The material age spec is defined here");
          break;

        default:
          break;
        }
        
      }
      
      vp.setDescript(new DescriptionSupport("Camel purse", "Camel hair made Jaipur purse"));
      vp.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
      vp.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      Assert.assertEquals(1, prodLine.getProducts().size());
      Assert.assertNotNull("VendorProduct entity was not merged", prodLine.getProducts().iterator().next().getId());
      
      vp = prodLine.newVendorProduct();
      vp.setActive(true);
      vp.setProdDispOrder(1);
      vp.setVendorProdLabel("Bull Purse A111");
      vp.setVendor(u);
      
      Assert.assertFalse(prodLine.getSpecs().isEmpty());
      for(ProductLineSpecification plSpec : prodLine.getSpecs())
      {
        VendorProductSpecification vSpec = vp.newSpecification(plSpec);
        switch (plSpec.getSpecCode()) {
        case "FORM":
          vSpec.setDescript("The form factor spec is defined here");
          break;
        case "TYPE":
          vSpec.setDescript("The material type spec is defined here");
          break;
        case "AGE":
          vSpec.setDescript("The material age spec is defined here");
          break;

        default:
          break;
        }
        
      }
      
      vp.setDescript(new DescriptionSupport("Bull purse", "Bull tail hair made Jaipur purse"));
      vp.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
      vp.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      
      //another product line in the same category
      prodLine = catg.newProductLine();
      prodLine.setCatgDispOrder(1);
      prodLine.setProdTypeLabel("JAISALMER POUCH");
      catg = catgRepo.saveAndFlush(catg);
      catg.getProducts().size();
      prodLine = catg.getProducts().iterator().next();
      
      Assert.assertNotNull("Product Line entity was not merged", prodLine.getId());
      prodLine = prodLineRepo.findOne(prodLine.getId());
      
      ps = prodLine.newProductSpecification();
      ps.setSpecCode("COLOR");
      ps.setLabel("Form Factor");
      ps = prodLine.newProductSpecification();
      ps.setSpecCode("CAPACITY");
      ps.setLabel("Type Factor");
      ps = prodLine.newProductSpecification();
      ps.setSpecCode("THICK");
      ps.setLabel("Thickness");
      
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      Assert.assertEquals(3, prodLine.getSpecs().size());
      Assert.assertNotNull("ProductLineSpecification entity was not merged", prodLine.getSpecs().iterator().next().getId());
      
      pt = prodLine.newProductTag();
      pt.setTag("wallet");
      pt = prodLine.newProductTag();
      pt.setTag("purse");
      pt = prodLine.newProductTag();
      pt.setTag("handbag");
      pt = prodLine.newProductTag();
      pt.setTag("pouch");
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      Assert.assertEquals(3, prodLine.getTags().size());
      Assert.assertNotNull("ProductTag entity was not merged", prodLine.getTags().iterator().next().getId());
      
      vp = prodLine.newVendorProduct();
      vp.setActive(true);
      vp.setProdDispOrder(1);
      vp.setVendor(u);
      vp.setVendorProdLabel("Camel Pouch A12");
      
      Assert.assertFalse(prodLine.getSpecs().isEmpty());
      for(ProductLineSpecification plSpec : prodLine.getSpecs())
      {
        VendorProductSpecification vSpec = vp.newSpecification(plSpec);
        switch (plSpec.getSpecCode()) {
        case "COLOR":
          vSpec.setDescript("The form factor spec is defined here");
          break;
        case "CAPACITY":
          vSpec.setDescript("The material type spec is defined here");
          break;
        case "THICK":
          vSpec.setDescript("The material age spec is defined here");
          break;

        default:
          break;
        }
        
      }
      
      vp.setDescript(new DescriptionSupport("Camel pouch", "Camel hair made Jaisalmer pouch. Gold colored"));
      vp.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
      vp.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      Assert.assertEquals(1, prodLine.getProducts().size());
      Assert.assertNotNull("VendorProduct entity was not merged", prodLine.getProducts().iterator().next().getId());
      
      vp = prodLine.newVendorProduct();
      vp.setActive(true);
      vp.setProdDispOrder(1);
      vp.setVendor(u);
      vp.setVendorProdLabel("Bull Pouch BP1");
      
      Assert.assertFalse(prodLine.getSpecs().isEmpty());
      for(ProductLineSpecification plSpec : prodLine.getSpecs())
      {
        VendorProductSpecification vSpec = vp.newSpecification(plSpec);
        switch (plSpec.getSpecCode()) {
        case "COLOR":
          vSpec.setDescript("The form factor spec is defined here");
          break;
        case "CAPACITY":
          vSpec.setDescript("The material type spec is defined here");
          break;
        case "THICK":
          vSpec.setDescript("The material age spec is defined here");
          break;

        default:
          break;
        }
        
      }
      
      vp.setDescript(new DescriptionSupport("Bull pouch", "Bull tail hair made Jaisalmer pouch. Black colored"));
      vp.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
      vp.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }
  
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Test
  @Rollback(false)
  public void testCreateSingleProductCatalog()
  {
    try 
    {
      catg = new Category();
      catg.setCategory("category");
      catg.setDescript(new DescriptionSupport("Sole category", "This is the only category in the universe"));
      catg = catgRepo.saveAndFlush(catg);
      
      prodLine = catg.newProductLine();
      prodLine.setCatgDispOrder(1);
      prodLine.setProdTypeLabel("prodTypeLabel");
      catg = catgRepo.saveAndFlush(catg);
      catg.getProducts().size();
      prodLine = catg.getProducts().iterator().next();
      
      Assert.assertNotNull("Product Line entity was not merged", prodLine.getId());
      prodLine = prodLineRepo.findOne(prodLine.getId());
      
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
      Assert.assertEquals(3, prodLine.getSpecs().size());
      Assert.assertNotNull("ProductLineSpecification entity was not merged", prodLine.getSpecs().iterator().next().getId());
      
      ProductTag pt = prodLine.newProductTag();
      pt.setTag("tag1");
      pt = prodLine.newProductTag();
      pt.setTag("tag2");
      pt = prodLine.newProductTag();
      pt.setTag("tag3");
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      Assert.assertEquals(3, prodLine.getTags().size());
      Assert.assertNotNull("ProductTag entity was not merged", prodLine.getTags().iterator().next().getId());
      User u = userRepo.findByUserName("admin");
      
      VendorProduct vp = prodLine.newVendorProduct();
      vp.setActive(true);
      vp.setProdDispOrder(1);
      vp.setVendorProdLabel("Camel Purse A111");
      vp.setVendor(u);
      
      Assert.assertFalse(prodLine.getSpecs().isEmpty());
      for(ProductLineSpecification plSpec : prodLine.getSpecs())
      {
        VendorProductSpecification vSpec = vp.newSpecification(plSpec);
        switch (plSpec.getSpecCode()) {
        case "FORM":
          vSpec.setDescript("The form factor spec is defined here");
          break;
        case "TYPE":
          vSpec.setDescript("The material type spec is defined here");
          break;
        case "AGE":
          vSpec.setDescript("The material age spec is defined here");
          break;

        default:
          break;
        }
        
      }
      
      vp.setDescript(new DescriptionSupport("Product Description", "This is an actual vendor product detailed description"));
      vp.setDimension(new ProductDimensionSupport(3.5, 1.5, 0.8, 250.0));
      vp.setCosting(new ProductCostingSupport(12.5, 1000.00, 55.0, 105.5));
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      Assert.assertEquals(1, prodLine.getProducts().size());
      Assert.assertNotNull("VendorProduct entity was not merged", prodLine.getProducts().iterator().next().getId());
      
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }
  //@After
  public void tearDown()
  {
    if(catg != null)
    {
      try {
        catgRepo.delete(catg);
      } catch (Exception e) {
        e.printStackTrace();
        Assert.fail(e.getMessage());
      }
    }
  }
  @Autowired
  private CategoryRepo catgRepo;
  @Autowired
  private ProductLineRepo prodLineRepo;
  @Autowired
  private UserRepo userRepo;
}
