package mzj.mandroid.utils;

public class CommConst {
    public static final boolean COMM_BEBUG = true;

    public static final int UDP_PORT = 9982;
    public static final int TCP_PORT = 9981;

    public static final byte DEFAULT_PRODUCT_ID = 0x01;
    public static final byte[] DEFAULT_CLIENT_QUERY_HEADER = {0x69, 0x70};
    public static final byte[] DEFAULT_PASSWD = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF};
    public static final byte[] DEFAULT_MAGIC = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a,
            0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

    public static final String RECEIVE_DEVICE_UDP_DATA = "RECEIVE_DEVICE_UDP_DATA";
    public static final String SEND_DEVICE_TCP_DATA = "SEND_DEVICE_TCP_DATA";
    public static final String RECEIVE_DEVICE_TCP_DATA = "RECEIVE_DEVICE_TCP_DATA";
    public static final String CONNECT_OVER_TIME = "CONNECT_OVER_TIME";

    public static final String ERROR_RECEIVE_DEVICE_TCP_DATA_HEAD = "ERROR_RECEIVE_DEVICE_TCP_DATA_HEAD";
    public static final String ERROR_RECEIVE_DEVICE_TCP_DATA_CRC = "ERROR_RECEIVE_DEVICE_TCP_DATA_CRC";
    public static final String ERROR_SEND = "ERROR_SEND";
    public static final String ERROR_SOCKET_BREAK = "ERROR_SOCKET_BREAK";
    public static final String CONNECT_FAILE = "CONNECT_FAILE";
    public static final String CONNECT_SUCCESS = "CONNECT_SUCCESS";

    //java.net.ConnectException: failed to connect to /192.168.1.103 (port 9981) after 90000ms: isConnected failed: ECONNREFUSED (Connection refused)

    //	public static final int TCP_MSG_DEVICE_INFO = 0x12401;
    //	public static final int TCP_MSG_DEVICE_STATUS = 0x12402;

    public static final int MTCommandTypeSearchDevice = 0;                // 发送UDP广播查找MT7681设备
    public static final int MTCommandTypeGPIOSetRequest = 1;
    public static final int MTCommandTypeGPIOSetResponse = 2;
    public static final int MTCommandTypeGPIOGetRequest = 3;
    public static final int MTCommandTypeGPIOGetResponse = 4;
    public static final int MTCommandTypeProductInfoGetRequest = 11;
    public static final int MTCommandTypeProductInfoGetResponse = 12;
    public static final int MTCommandTypeIOTReportInfoToCloud = 33;
    public static final int MTCommandTypeIOTHeartToCloud = 34;
    public static final int MTCommandTypeIOTReportInfoToApp1 = 35;        // 设备与APP第一次连接需要上传自身信息
    public static final int MTCommandTypeIOTReportInfoToApp2 = 36;        // 设备向APP发送的心跳包

    public static boolean isUdpToReceive = true;


    public static String DeviceID ;
    public static String DeviceIP;
}
