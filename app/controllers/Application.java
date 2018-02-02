package controllers;

import models.Computer;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;
import play.db.jpa.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import views.html.*;
import play.libs.Json;


public class Application extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public Result GO_HOME = redirect(
            routes.Application.list(0, "name", "asc", "")
    );


    /**
     * Describes the hello form.
     */
    public static class Hello {
        @Required public String name;
        @Required @Min(1) @Max(100) public Integer repeat;
        public String color;
    } 
    
    // -- Actions
  
    /**
     * Home page
     */
    public Result index() {
        /*return ok(
            index.render(form(Hello.class))
        );*/
        return GO_HOME;
    }
  
    /**
     * Handles the form submission.
     */
    public Result sayHello() {
        Form<Hello> form = form(Hello.class).bindFromRequest();
        if(form.hasErrors()) {
            //return badRequest(index.render(form));
            return GO_HOME;
        } else {
            Hello data = form.get();
            /*return ok(
                hello.render(data.name, data.repeat, data.color)
            );*/
            return GO_HOME;
        }
    }

    /**
     * Display the 'new computer form'.
     */
    @Transactional(readOnly=true)
    public Result create() {
        /*Form<Computer> computerForm = form(Computer.class);
        return ok(
                createForm.render(computerForm)
        );
          */
        ObjectNode result = Json.newObject();
        result.put("id", "1");
        //result.put("exampleField2", "Hello world!");
        return ok(result);
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    @Transactional(readOnly=true)
    public Result list(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Computer.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    @Transactional(readOnly=true)
    public Result edit(Long id) {
        Form<Computer> computerForm = form(Computer.class).fill(
                Computer.findById(id)
        );
        return ok(
                editForm.render(id, computerForm)

        );
    }

    /**
     * Handle computer deletion
     */
    @Transactional
    public Result delete(Long id) {
        Computer.findById(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }

    /**
     * Handle the 'edit form' submission
     *
     * @param id Id of the computer to edit
     */
    @Transactional
    public Result update(Long id) {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(editForm.render(id, computerForm));
        }
        computerForm.get().update(id);
        flash("success", "Computer " + computerForm.get().name + " has been updated");
        return GO_HOME;
    }

}
