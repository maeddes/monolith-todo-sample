package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Profile("nomq")
@RequestMapping("/")
public class ToDoListCommandControllerSync {

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String addItem(ToDoItem toDoItem){

        System.out.println("In addItem: "+toDoItem);

        try {
            this.toDoItemRepository.save(toDoItem);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/done/{id}", method = RequestMethod.POST)
    public String setItemDone(@PathVariable int id){

        System.out.println("In setItemDone: "+id);
  
        try {
            this.toDoItemRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return "redirect:/";

    }
}

