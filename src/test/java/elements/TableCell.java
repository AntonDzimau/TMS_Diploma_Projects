package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableCell {
    private UIElement uiElement;

    public TableCell(WebDriver driver, By by) {
        this.uiElement = new UIElement(driver, by);
    }

    public TableCell(WebDriver driver, WebElement webElement) {
        this.uiElement = new UIElement(driver, webElement);
    }

    public TableCell(WebDriver driver, UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public UIElement getLinkFromCell() {
       return uiElement.findElement(By.tagName("a"));
    }
}
