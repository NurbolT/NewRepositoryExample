
package com.item.controller;

import com.item.dao.ItemDAO;
import com.item.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Controller2
public class ItemController {

    @Autowired
    ItemDAO itemDAO;

    //Some comments was added

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allItems(){
        List<Item> itemList = itemDAO.allItems();
        ModelAndView view = new ModelAndView();
        view.addObject("examples", itemList);
        view.setViewName("items");
        return  view;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable(name = "id") Long id){
        Item item = itemDAO.getById(id);
        ModelAndView view = new ModelAndView();
        view.addObject("item",item);
        view.setViewName("editItemsExample");
        return view;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addItem(@RequestParam(name = "name") String name,
                         @RequestParam(name = "price_name") double price){

        itemDAO.add(new Item(null, name, price));

        return "redirect:/";

    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable(name = "id") Long id){

        Item item = itemDAO.getById(id);
        itemDAO.delete(item);
        return "redirect:/";

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateItem(@RequestParam(name = "name") String name,
                             @RequestParam(name = "price") double price,
                             @RequestParam(name = "id") Long id){

        itemDAO.edit(new Item(id, name, priceExampleUsed));

        return "redirect:/";

    }


}