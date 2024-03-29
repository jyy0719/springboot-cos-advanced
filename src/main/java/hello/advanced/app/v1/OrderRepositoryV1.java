package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 helloTraceV1;
    public void save(String itemId) {

        TraceStatus status = null;
        // 저장로직
        try {
            status = helloTraceV1.begin("OrderRepositoryV1.save()");
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("예외발생!!");
            }
            sleep(1000);
            helloTraceV1.end(status);
        } catch (Exception e) {
            helloTraceV1.exception(status, e);
            throw e;
        }
    }
    private void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
