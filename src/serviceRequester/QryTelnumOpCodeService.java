package serviceRequester;

import cht.ccsdk.bean.AuthTokenBean;
import cht.ccsdk.proxy.ServiceManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public class QryTelnumOpCodeService
{
  private String isvAccount;
  private String isvKey;

  public QryTelnumOpCodeService(String paramString1, String paramString2)
  {
    this.isvAccount = paramString1;
    this.isvKey = paramString2;
  }

  public String getQryResult(String paramString)
  {
    String localObject = "";
    try
    {
      String str1 = "50";
      String str2 = Long.toString(ServiceManager.genTimestamp());
      String str3 = ServiceManager.genNonce();
      Proxy localProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.hinet.net", 80));
      ServiceManager localServiceManager = new ServiceManager("http", "api.hicloud.hinet.net", 80, localProxy);

      AuthTokenBean localAuthTokenBean = localServiceManager.requestToken(this.isvAccount, str1, str3, str2, ServiceManager.genSign(this.isvKey + str3 + str2, "SHA"));

      if (localAuthTokenBean == null) {
        return "<?xml version=\"1.0\" encoding=\"Big5\"?><BMS><Status>7</Status><Msg>無法取得授權碼，請檢查isvAccount和isvKey</Msg><TelNum>" + paramString + "</TelNum></BMS>";
      }

      String str4 = "";
      try {
        StringBuffer localStringBuffer = new StringBuffer();

        String str5 = "http://cht-emome-api.hicloud.net.tw/emome/ebms/cloud.jsp?Service=QryTelnumOpCodeCloud";
        str5 = str5 + "&TelNum=" + paramString;
        str5 = str5 + "&token=" + localAuthTokenBean.getToken() + "&sign=" + localAuthTokenBean.getSign();
        URL localURL = new URL(str5);
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection(localProxy);
        localHttpURLConnection.setRequestMethod("GET");

        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream(), "big5" ) );
        while ((str4 = localBufferedReader.readLine()) != null) {
          localStringBuffer.append(str4);
        }
        localBufferedReader.close();
        str4 = localStringBuffer.toString();
        localObject = str4;
      } catch (Exception localException2) {
        return "<?xml version=\"1.0\" encoding=\"Big5\"?><BMS><Status>8</Status><Msg>呼叫Service Provider失敗</Msg><TelNum>" + paramString + "</TelNum></BMS>";
      }
    } catch (Exception localException1) {
      localException1.printStackTrace();
    }
    return localObject;
  }
}