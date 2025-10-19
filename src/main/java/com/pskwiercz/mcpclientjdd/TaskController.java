package com.pskwiercz.mcpclientjdd;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ChatClient chatClient;

    public TaskController(ChatClient.Builder chatClientBuilder,
                          ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/{id}")
    String getTask(@PathVariable String id) {
        return this.chatClient.prompt()
                .user("Show me task with id " + id)
                .call().content();
    }

    @GetMapping("/type/{type}")
    String findByType(@PathVariable String type) {
        return this.chatClient.prompt()
                .user("Find all task with " + type + " type.")
                .call().content();
    }

    @GetMapping("/type-count/{type}")
    String countByType(@PathVariable String type) {
        return chatClient.prompt()
                .user("How many tasks have type " + type + " ?")
                .call().content();
    }

    @GetMapping("/all")
    String getAllTask() {
        return chatClient.prompt()
                .user("Show me all my tasks")
                .call().content();
    }
}
