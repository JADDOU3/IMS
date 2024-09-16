package org.example.optionsmanager;

import lombok.Data;

@Data
public class Option {
    private String description;
    private Runnable task;

    public Option(String description) {
        this.description = description;
    }

    public void run() {
        if (task != null) {
            task.run();
        }
    }
}
