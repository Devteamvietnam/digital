package com.devteam.lib.util.jvm;

import com.vion.util.text.DateUtil;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class JVMInfo {

  private String                          startTime;
  private String                          upTime;

  private MemoryInfo                      memoryInfo;
  private ArrayList<com.vion.util.jvm.GarbageCollectorInfo> garbageCollectorInfo;
  private com.vion.util.jvm.JVMThreads threads;

  public JVMInfo() { }

  public JVMInfo init() {
    startTime = DateUtil.asCompactDateTime(ManagementFactory.getRuntimeMXBean().getStartTime()) ;
    upTime = DateUtil.asHumanReadable(ManagementFactory.getRuntimeMXBean().getUptime()) ;
    memoryInfo = new MemoryInfo().init();

    List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans() ;
    garbageCollectorInfo = new ArrayList<com.vion.util.jvm.GarbageCollectorInfo>();
    for(int i = 0; i < gcbeans.size(); i++) {
      GarbageCollectorMXBean gcbean = gcbeans.get(i) ;
      garbageCollectorInfo.add(new com.vion.util.jvm.GarbageCollectorInfo(gcbean));
    }
    threads = new com.vion.util.jvm.JVMThreads();
    return this;
  }

  public String getStartTime() { return startTime; }
  public void setStartTime(String startTime) { this.startTime = startTime; }

  public String getUpTime() { return upTime; }
  public void setUpTime(String upTime) { this.upTime = upTime; }

  public MemoryInfo getMemoryInfo() { return memoryInfo; }
  public void setMemoryInfo(MemoryInfo memoryInfo) { this.memoryInfo = memoryInfo; }

  public ArrayList<com.vion.util.jvm.GarbageCollectorInfo> getGarbageCollectorInfo() { return garbageCollectorInfo; }
  public void setGarbageCollectorInfo(ArrayList<com.vion.util.jvm.GarbageCollectorInfo> garbageCollectorInfo) {
    this.garbageCollectorInfo = garbageCollectorInfo;
  }

  public com.vion.util.jvm.JVMThreads getThreads() { return threads; }
  public void setThreads(com.vion.util.jvm.JVMThreads threads) { this.threads = threads; }
}