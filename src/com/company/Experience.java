package com.company;

import java.util.ArrayList;
import java.util.List;

public class Experience {
    private String company;
    private String title;
    private String date;
    private List<String> descriptions;

    public Experience() {
        descriptions = new ArrayList<>();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String output = title + "\n" +
                        company + ", " + date + "\n";

        for (int i = 0; i < descriptions.size(); i++) {
            output +=  "- Duty " + (i + 1) +", " + descriptions.get(i) + "\n";
        }
        return output;
    }
}
