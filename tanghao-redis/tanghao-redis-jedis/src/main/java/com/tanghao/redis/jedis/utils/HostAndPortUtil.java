package com.tanghao.redis.jedis.utils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.List;

public final class HostAndPortUtil {
  private static List<HostAndPort> clusterHostAndPortList = new ArrayList<HostAndPort>();

  private HostAndPortUtil(){
    throw new InstantiationError( "Must not instantiate this class" );
  }

  static {

    clusterHostAndPortList.add(new HostAndPort("localhost", 7379));
    clusterHostAndPortList.add(new HostAndPort("localhost", 7380));
    clusterHostAndPortList.add(new HostAndPort("localhost", 7381));
    clusterHostAndPortList.add(new HostAndPort("localhost", 7382));
    clusterHostAndPortList.add(new HostAndPort("localhost", 7383));
    clusterHostAndPortList.add(new HostAndPort("localhost", 7384));

    String envClusterHosts = System.getProperty("cluster-hosts");

    clusterHostAndPortList = parseHosts(envClusterHosts, clusterHostAndPortList);
  }

  public static List<HostAndPort> parseHosts(String envHosts,
                                             List<HostAndPort> existingHostsAndPorts) {

    if (null != envHosts && 0 < envHosts.length()) {

      String[] hostDefs = envHosts.split(",");

      if (null != hostDefs && 2 <= hostDefs.length) {

        List<HostAndPort> envHostsAndPorts = new ArrayList<HostAndPort>(hostDefs.length);

        for (String hostDef : hostDefs) {

          String[] hostAndPortParts = HostAndPort.extractParts(hostDef);

          if (null != hostAndPortParts && 2 == hostAndPortParts.length) {
            String host = hostAndPortParts[0];
            int port = Protocol.DEFAULT_PORT;

            try {
              port = Integer.parseInt(hostAndPortParts[1]);
            } catch (final NumberFormatException nfe) {
            }

            envHostsAndPorts.add(new HostAndPort(host, port));
          }
        }

        return envHostsAndPorts;
      }
    }

    return existingHostsAndPorts;
  }

  public static List<HostAndPort> getClusterServers() {
    return clusterHostAndPortList;
  }
}
