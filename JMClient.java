public class JMClient
  extends AbstractJavaSamplerClient{
  private String locator;
  private int port;
  private String region;
  private SampleResult results;
  UtilsTools tools = new UtilsTools();
  
  public void setupTest(JavaSamplerContext jsc)  {
    this.results = new SampleResult();
    this.locator = jsc.getParameter("locator", "");
    this.port = Integer.parseInt(jsc.getParameter("port", ""));
    this.region = jsc.getParameter("region", "");
    if ((this.locator != null) && (this.locator.length() > 0)) {
      this.results.setSamplerData(this.locator);
    }
   if ((this.region != null) && (this.region.length() > 0)) {
      this.results.setSamplerData(this.region);
    }
this.results.setSampleCount(this.port);
System.out.println("init params---------------------------------------port=" + this.port + " locator=" + this.locator + " region=" + this.region);
  }
  
  public Arguments getDefaultParameters()  {
    Arguments params = new Arguments();
    params.addArgument("port", "");
    params.addArgument("region", "");
    params.addArgument("locator", "");
    return params;
  }
  
  public SampleResult runTest(JavaSamplerContext arg0)  {
    this.results.sampleStart();
    ClientCache cache = new ClientCacheFactory().addPoolLocator(this.locator, this.port).setPoolSubscriptionEnabled(true)
      .setPoolSubscriptionRedundancy(1).setPoolReadTimeout(100000).setPdxReadSerialized(true)
      .set("log-level", "info").create();

        String oql = "select * from /" + this.region;
        QueryService service = cache.getQueryService();
        Query query = service.newQuery(oql);
    try {
      SelectResults<String> result = (SelectResults)query.execute();
      String content = "";
      for (String value : result) {
        content = content + value + "\r\n";
      }
      this.results.setResponseMessage(content);
      this.results.setDataEncoding("UTF-8");
      this.results.setResponseData("返回值：" + content);
      
      this.results.setSuccessful(true);
      this.results.sampleEnd();
    }
    catch (Exception e)    {
      e.printStackTrace();
    }
    finally {
      if (cache != null) {
        cache.close();
      }
    }
    return this.results;
  }
  
  public void teardownTest(JavaSamplerContext arg0) {}
}
