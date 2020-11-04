package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

@Profile("rabbit")
@RabbitListener(queues = "my-queue")
public class ToDoListCommandReceiver {

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @RabbitHandler
    public void receive(String in) {
        System.out.println("Received '" + in + "'");
        if (in.startsWith("done:")) {

            // unsafe hack
            String id = in.split(":")[1];
            if (id != null) {

                try {
                    this.toDoItemRepository.deleteById(new Integer(in).intValue());
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }
            return;

        } else {

            try {
                this.toDoItemRepository.save(new ToDoItem(in));
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }

}
