package bytebuddy.agentdemo;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JvmInfo {
    static void printMemoryInfo(){
        final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        final MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        final MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        final long init = heapMemoryUsage.getInit();
        final long committed =heapMemoryUsage.getCommitted();
        final long max =heapMemoryUsage.getMax();
        final long used = heapMemoryUsage.getUsed();
        final String rate=used * 100 / committed + "%";
        final long committedNon =nonHeapMemoryUsage.getCommitted();
        final long initNon =nonHeapMemoryUsage.getInit();
        final long maxNon =nonHeapMemoryUsage.getMax();
        final long usedNon =nonHeapMemoryUsage.getUsed();
        final String rateNon=usedNon * 100 / committedNon + "%";

        log.info("\n init: {}\t max: {}\t used: {}\t committed: {}\t use rate: {}\n",init,max,used,committed,rate);
        log.info("\n initNon: {}\t maxNon: {}\t usedNon: {}\t committedNon: {}\t useNon rate: {}\n",initNon,maxNon,usedNon,committedNon,rateNon);

    }
    public static void printGC(){
        final List<GarbageCollectorMXBean> memoryManagerMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : memoryManagerMXBeans) {
            String info = "\n name: {}\t count:{}\t took:{}\t pool name:{}";
            String name=garbage.getName();
            long count=garbage.getCollectionCount();
            long time=garbage.getCollectionTime();
            String deep=Arrays.deepToString(garbage.getMemoryPoolNames());
            log.info(info,name,count,time,deep);
        }
    }
}
