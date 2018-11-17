package myshop.com.myshop.model;

public class Product {

    private ItemCategory[] item_categories = null;

    private String job_flag;

    private String auth_token;

    public String getJob_flag() {
        return job_flag;
    }

    public void setJob_flag(String job_flag) {
        this.job_flag = job_flag;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }


    public ItemCategory[] getItemCategories() {
        return item_categories;
    }

    public void setItemCategories(ItemCategory[] itemCategories) {
        this.item_categories = itemCategories;
    }

}