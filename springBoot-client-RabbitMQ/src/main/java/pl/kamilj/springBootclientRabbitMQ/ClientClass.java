package pl.kamilj.springBootclientRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientClass {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sentMessage")
    public String Message(@RequestParam String message) {

        rabbitTemplate.convertAndSend("testQueue", message);

        return "send!";
    }

    @GetMapping("/receiveMessage")
    public String getMessage() {

        Object message = rabbitTemplate.receiveAndConvert("testQueue");

        return message.toString();
    }

//    @RabbitListener(queues = "testQueue")
//    public void Listener(String message){
//        System.out.println(message);
//    }
}
