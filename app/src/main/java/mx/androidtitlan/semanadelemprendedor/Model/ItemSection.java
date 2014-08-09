package mx.androidtitlan.semanadelemprendedor.Model;


import mx.androidtitlan.semanadelemprendedor.util.ListViewItemInterface;

/**
 * Created by Jhordan on 20/07/14.
 */
public class ItemSection implements ListViewItemInterface {

    private String titleSection;
    private int backgroundColor;
    private int textColor;
    private int sectionTitleFromResources;


    public ItemSection(){

    }


    public ItemSection(String titleSection, int backgroundColor, int textColor) {
        this.titleSection = titleSection;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public ItemSection(int backgroundColor, int textColor, int sectionTitleFromResources) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.sectionTitleFromResources = sectionTitleFromResources;
    }

    public ItemSection(String titleSection) {
        this.titleSection = titleSection;
    }

    public ItemSection(int sectionTitleFromResources) {
        this.sectionTitleFromResources = sectionTitleFromResources;
    }

    public String getTitleSection() {
        return titleSection;
    }

    public void setTitleSection(String titleSection) {
        this.titleSection = titleSection;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSectionTitleFromResources() {
        return sectionTitleFromResources;
    }

    public void setSectionTitleFromResources(int titleSectionFromResources) {
        this.sectionTitleFromResources = titleSectionFromResources;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}