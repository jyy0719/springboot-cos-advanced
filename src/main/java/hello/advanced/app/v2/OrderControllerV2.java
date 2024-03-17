package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderServiceV0;
    private final HelloTraceV2 helloTraceV2;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status =null;
        try {
            status = helloTraceV2.begin("OrderControllerV2.request()");
            orderServiceV0.orderItem(status.getTraceId(),itemId);
            helloTraceV2.end(status);
            return "ok";
        } catch (Exception e) {
            helloTraceV2.exception(status, e);
            throw e; // 예외를 다시 던져주어야한다. 던지지 않으면 예외를 먹기만하고 끝난다.
        }
    }
}
