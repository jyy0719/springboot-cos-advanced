package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV0;
    private final HelloTraceV1 helloTraceV1;

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status =null;
        try {
            status = helloTraceV1.begin("OrderControllerV1.request()");
            orderServiceV0.orderItem(itemId);
            helloTraceV1.end(status);
            return "ok";
        } catch (Exception e) {
            helloTraceV1.exception(status, e);
            throw e; // 예외를 다시 던져주어야한다. 던지지 않으면 예외를 먹기만하고 끝난다.
        }
    }
}
