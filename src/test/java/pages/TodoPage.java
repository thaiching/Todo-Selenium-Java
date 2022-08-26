package pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.stream.Collectors;

public class TodoPage extends BasePage{
    private final By inputNewTodo = By.cssSelector(".new-todo");
    private final By todos = By.cssSelector(".todo-list > li > .view > label");
    private final By todoCount = By.cssSelector(".todo-count strong");
    private final By completedTodos = By.cssSelector(".todo-list li.completed label");
    private final By todoToggle = By.cssSelector(".toggle");
    private final By header = By.tagName("h1");
    private final By todoList = By.className("todo-list");

    private final By clearCompletedBtn = By.className("clear-completed");
    private WebElement activeTodoEdit;
    private WebElement activeTodoToBeDeleted;
    public TodoPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> findTodos() {
        return findAll(todos);
    }

    private List<WebElement> findCompletedTodos(){
        return findAll(completedTodos);
    }

    public WebElement findNewTodoButton() {
        final Wait<WebDriver> wait = new WebDriverWait(getDriver(), 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputNewTodo));
    }

    public List<String> getActiveTodosText() {
        return findTodos().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getCompletedTodos(){
        return findCompletedTodos().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getHeader(){
        return find(header).getText();
    }

    public int getTodoCount(){
        return Integer.parseInt(find(todoCount).getText());
    }

    public boolean isTodoListVisible(){
        return find(todoList).isDisplayed();
    }

    public void createNewTodo(String todo){
        findNewTodoButton().sendKeys(todo);
        findNewTodoButton().sendKeys(Keys.ENTER);
    }

    public void createTodos(DataTable table){
        List<String> rows = table.asList();

        for(int i=0; i<rows.size();i++){
            createNewTodo(rows.get(i));
        }
    }

    public void filterTodos(String filterType){
        find(By.cssSelector("a[href=\"#/" + filterType + "\"]")).click();
    }

    public void updateTodo(int index, String newTodo){
        makeTodoEditable(index);
        clearTodo(index);
        updateTodo(newTodo);
    }

    public void makeTodoEditable(int index){
        Actions action = new Actions(getDriver());
        WebElement el = findTodo(index);
        action.doubleClick(el).perform();
        activeTodoEdit = findActiveTodoEditInput(index);
    }

    public void updateTodo(String newTodo){
        activeTodoEdit.sendKeys(newTodo);
        activeTodoEdit.sendKeys(Keys.ENTER);
    }

    public void clearTodo(int index){
        int length = activeTodoEdit.getAttribute("value").length();
        for (int i = 0; i < length; i++){
            activeTodoEdit.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void deleteTodo(int index){
        Actions action = new Actions(getDriver());
        WebElement todoToBeDeleted = findTodo(index);
        action.moveToElement(todoToBeDeleted).perform();
        activeTodoToBeDeleted = findActiveTodoToBeDeleted(todoToBeDeleted);
        activeTodoToBeDeleted.click();
    }

    public void completeTodo(int index){
        findTodo(index).findElement(todoToggle).click();
    }

    public void clearCompletedTodos(){
        find(clearCompletedBtn).click();
    }

    public WebElement findActiveTodoEditInput(int index) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 5);
        WebElement activeTodoEditInput = findTodo(index).findElement(By.cssSelector(".edit"));
        return wait.until(ExpectedConditions.elementToBeClickable(activeTodoEditInput));
    }

    public WebElement findActiveTodoToBeDeleted(WebElement element){
        return element.findElement(By.cssSelector(".destroy"));
    }

    public WebElement findTodo(int index){
        return find(By.cssSelector(".todo-list > li:nth-child(" + index + ")"));
    }

    public void emptyList(){
        if (getDriver() instanceof WebStorage) {
            WebStorage webStorage = (WebStorage)getDriver();
            webStorage.getLocalStorage().clear();
        }
        getDriver().navigate().refresh();
    }
}
