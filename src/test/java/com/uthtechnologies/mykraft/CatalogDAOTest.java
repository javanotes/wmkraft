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

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.uthtechnologies.mykraft.dao.CategoryRepo;
import com.uthtechnologies.mykraft.dao.ProductLineRepo;
import com.uthtechnologies.mykraft.dao.entity.catalog.Category;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLine;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductLineSpecification;
import com.uthtechnologies.mykraft.dao.entity.catalog.ProductTag;
import com.uthtechnologies.mykraft.dao.entity.catalog.VendorProduct;
import com.uthtechnologies.mykraft.dao.entity.util.DescriptionSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
@WebAppConfiguration
public class CatalogDAOTest {

  private Category catg;
  private ProductLine prodLine;
  @Transactional
  @Test
  @Rollback(false)
  public void testCreateProductCatalog()
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
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      ProductTag pt = prodLine.newProductTag();
      pt.setTag("tag1");
      pt = prodLine.newProductTag();
      pt.setTag("tag2");
      pt = prodLine.newProductTag();
      pt.setTag("tag3");
      prodLine = prodLineRepo.saveAndFlush(prodLine);
      
      //VendorProduct vp = prodLine.newVendorProduct();
      
      
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }
  @After
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
}
