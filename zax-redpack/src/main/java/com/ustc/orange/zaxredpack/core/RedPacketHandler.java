package com.ustc.orange.zaxredpack.core;

public interface RedPacketHandler {

  <R extends BaseRedPack<?>> void handle(R r);
}
