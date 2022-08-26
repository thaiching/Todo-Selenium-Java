package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import pages.TodoPage;

public class Todo_steps {

    WebDriver driver;
    TodoPage objTodo;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vue/");
    }

    @Given("I am on the todo page")
    public void homeTest(){
        objTodo = new TodoPage(driver);
        String header = objTodo.getHeader();
        Assert.assertTrue(header.contains("todos"));
    }

    @And("I have an empty todo list")
    public void empty_todo_list(){
        objTodo.emptyList();
        Assert.assertFalse(objTodo.isTodoListVisible());
    }

    @When("I create a new todo {string}")
    public void create_new_todo(String todo){
        objTodo.createNewTodo(todo);
    }

    @Then("I see the new todo {string} on my list")
    public void verify_todo_result(String todo){
        Assert.assertEquals(todo, objTodo.getActiveTodosText().get(0));
        Assert.assertEquals(1, objTodo.getTodoCount());
    }

    @And("I edit todo {int} with {string}")
    public void edit_existing_todo(int index, String updatedTodo){
        objTodo.updateTodo(index, updatedTodo);
    }

    @And("I delete todo list in position {int}")
    public void delete_todo(int index){
        objTodo.deleteTodo(index);
    }

    @Then("the todo list is empty")
    public void empty_todolist(){
        Assert.assertEquals(0, objTodo.getActiveTodosText().size());
    }

    @When("I complete to do list in position {int}")
    public void complete_todo(int index){
        objTodo.completeTodo(index);
    }

    @Then("{int} todo list is completed")
    public void verify_completed_todos(int count){
        Assert.assertEquals(count, objTodo.getCompletedTodos().size());
    }

    @When("I have these todos on my list")
    public void create_todos(DataTable table){
        objTodo.createTodos(table);
    }

    @When("^I filter (active|completed) todos$")
    public void filter_todos(String filterType){
        objTodo.filterTodos(filterType);
    }
    @When("I clear completed todos")
    public void clear_completed(){
        objTodo.clearCompletedTodos();
    }

    @Then("{int} todo list is active")
    public void verify_active_todo_count(int count){
        Assert.assertEquals(count, objTodo.getActiveTodosText().size());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
