package org.consume.com.api;

/*
 * 创建日期 2014-3-13
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.io.UnsupportedEncodingException;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.SimpleHttpConnectionManager;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpClientParams;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.eclipse.jetty.util.ajax.JSON;

//import net.sf.json.JSONObject;

/**
 * @author administrator
 *
 *         Http Client工具类 发起http client 请求
 * 
 *         窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public final class HttpClientUtil
{

	private static final String TYPE_STRING = "string";

	private static final String TYPE_BYTEARRAY = "byte[]";

	private static final String TYPE_STREAM = "stream";

	private static HttpClientUtil instance;

	private HttpClientUtil()
	{
	}

	/**
	 * 使用默认的页面请求编码：utf-8
	 * 
	 * @return
	 */
	public static HttpClientUtil getInstance()
	{
		return getInstance("UTF-8");
	}

	public static HttpClientUtil getInstance(String urlCharset)
	{
		if (instance == null)
		{
			instance = new HttpClientUtil();
		}
		// 设置默认的url编码
		instance.setUrlCharset(urlCharset);
		return instance;
	}

	/**
	 * 请求编码，默认使用utf-8
	 */
	private static String urlCharset = "UTF-8";

	/**
	 * @param urlCharset
	 *            要设置的 urlCharset。
	 */
	@SuppressWarnings("static-access")
	public void setUrlCharset(String urlCharset)
	{
		this.urlCharset = urlCharset;
	}

//	/**
//	 * 获取字符串型返回结果，通过发起http post请求
//	 *
//	 * @param targetUrl
//	 * @param params
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("rawtypes")
//	public static String getResponseBodyAsString(String targetUrl, Map params)
//			throws Exception
//	{
//
//		return (String) setPostRequest(targetUrl, params, TYPE_STRING, null);
//	}

//	/**
//	 * 获取字符数组型返回结果，通过发起http post请求
//	 *
//	 * @param targetUrl
//	 * @param params
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("rawtypes")
//	public byte[] getResponseBodyAsByteArray(String targetUrl, Map params)
//			throws Exception
//	{
//
//		return (byte[]) setPostRequest(targetUrl, params, TYPE_BYTEARRAY, null);
//	}

//	/**
//	 * 将response的返回流写到outputStream中，通过发起http post请求
//	 *
//	 * @param targetUrl
//	 *            请求地址
//	 * @param params
//	 *            请求参数<paramName,paramValue>
//	 * @param outputStream
//	 *            输出流
//	 * @throws Exception
//	 */
//	@SuppressWarnings("rawtypes")
//	public void getResponseBodyAsStream(String targetUrl, Map params,
//			OutputStream outputStream) throws Exception
//	{
//		if (outputStream == null)
//		{
//			throw new IllegalArgumentException(
//					"调用HttpClientUtil.setPostRequest方法，targetUrl不能为空!");
//		}
//
//		// response 的返回结果写到输出流
//		setPostRequest(targetUrl, params, TYPE_STREAM, outputStream);
//	}

//	/**
//	 * 利用http client模拟发送http post请求
//	 *
//	 * @param targetUrl
//	 *            请求地址
//	 * @param params
//	 *            请求参数<paramName,paramValue>
//	 * @return Object 返回的类型可能是：String 或者 byte[] 或者 outputStream
//	 * @throws Exception
//	 */
	@SuppressWarnings({ "rawtypes", "unused" })
//	private static Object setPostRequest(String targetUrl, Map params,
//			String responseType, OutputStream outputStream) throws Exception
//	{
//		if (StringUtils.isBlank(targetUrl))
//		{
//			throw new IllegalArgumentException(
//					"调用HttpClientUtil.setPostRequest方法，targetUrl不能为空!");
//		}
//
//		Object responseResult = null;
//		HttpClient client = null;
//		PostMethod post = null;
//		NameValuePair[] nameValuePairArr = null;
//		SimpleHttpConnectionManager connectionManager = null;
//		try
//		{
//			connectionManager = new SimpleHttpConnectionManager(true);
//			// 连接超时,单位毫秒
//			connectionManager.getParams().setConnectionTimeout(3000);
//			// 读取超时,单位毫秒
//			connectionManager.getParams().setSoTimeout(60000);
//
//			// 设置获取内容编码
//			connectionManager.getParams().setParameter(
//					HttpMethodParams.HTTP_CONTENT_CHARSET, urlCharset);
//			client = new HttpClient(new HttpClientParams(), connectionManager);
//
//			post = new PostMethod(targetUrl);
//			// 设置请求参数的编码
//			post.getParams().setContentCharset(urlCharset);
//
//			// 服务端完成返回后，主动关闭链接
//			post.setRequestHeader("Connection", "close");
//			if (params != null)
//			{
//				nameValuePairArr = new NameValuePair[params.size()];
//
//				Set key = params.keySet();
//				Iterator keyIte = key.iterator();
//				int index = 0;
//				while (keyIte.hasNext())
//				{
//					Object paramName = keyIte.next();
//					Object paramValue = params.get(paramName);
//					if (paramName instanceof String
//							&& paramValue instanceof String)
//					{
//						NameValuePair pair = new NameValuePair(
//								(String) paramName, (String) paramValue);
//						nameValuePairArr[index] = pair;
//						index++;
//					}
//				}
//
//				post.addParameters(nameValuePairArr);
//			}
//
//			int sendStatus = client.executeMethod(post);
//
//			if (sendStatus == HttpStatus.SC_OK)
//			{
//				// System.out.println("HttpClientUtil.setPostRequest()-responseType:"+responseType);
//
//				if (StringUtils.equals(TYPE_STRING, responseType))
//				{
//					responseResult = post.getResponseBodyAsString();
//				} else if (StringUtils.equals(TYPE_BYTEARRAY, responseType))
//				{
//					responseResult = post.getResponseBody();
//				} else if (StringUtils.equals(TYPE_STREAM, responseType))
//				{
//					InputStream tempStream = post.getResponseBodyAsStream();
//					byte[] temp = new byte[1024];
//					int index = -1;
//					while ((index = tempStream.read(temp)) != -1)
//					{
//						outputStream.write(temp);
//					}
//				}
//			} else
//			{
//				System.err.println("***************************");
//				System.err.println("HttpClientUtil.setPostRequest()-请求url："
//						+ targetUrl + " 出错\n请求参数有：" + JSON.toString(params)
//						+ "！！！");
//				System.err.println("***************************");
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		} finally
//		{
//			// 释放链接
//			if (post != null)
//			{
//				post.releaseConnection();
//			}
//
//			// 关闭链接
//			if (connectionManager != null)
//			{
//				connectionManager.shutdown();
//			}
//		}
//
//		return responseResult;
//	}
//
//	/**
//	 * post请求
//	 *
//	 * @param url
//	 * @param json
//	 * @return
//	 */
//
//	@SuppressWarnings("unused")
//	public static String doPost(String url, JSONObject json)
//	{
//		// 创建HttpClientBuilder
//		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//		// HttpClient
//		CloseableHttpClient client = httpClientBuilder.build();
//		HttpPost post = new HttpPost(url);
//		String response = null;
//		try
//		{
//			StringEntity s = new StringEntity(json.toString(), "utf-8");
//			// s.setContentEncoding("iso-8859-1");
//			s.setContentType("application/json");// 发送json数据需要设置contentType
//			post.setEntity(s);
//			HttpResponse res = client.execute(post);
//			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
//			{
//				HttpEntity entity = res.getEntity();
//				String result0 = EntityUtils.toString(res.getEntity());// 返回json格式：
//				// String result = urlEncodeUTF8(result0);// 解决中文乱码
//				response = result0;
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//			// throw new RuntimeException(e);
//		}
//		return response;
//	}
//
//	public static String doPost1(String url, String soapHeader1)
//	{
//		CloseableHttpClient client = HttpClients.createDefault();
//		HttpPost post = new HttpPost(url);
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
//		post.setConfig(requestConfig);
//		String result = null;
//		try
//		{
//			StringEntity se = new StringEntity(soapHeader1, "gbk");
//			post.setEntity(se);
//			post.setHeader("Connection", "Keep-Alive");
//			post.setHeader("Content-Type", "text/xml; charset=gbk");
//			post.setHeader("SOAPAction",
//					"http://WebXml.com.cn/getSupportCityString");
//			HttpResponse res = client.execute(post);
//
//			System.out.println(res.getStatusLine().getStatusCode());
//			System.out.println(HttpStatus.SC_OK);
//			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
//			{
//				byte[] byteArray = EntityUtils.toByteArray(res.getEntity());// 返回数据
//				result = new String(byteArray);
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return result;
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static String doPost2(String url, List list)
//	{
//		CloseableHttpClient client = HttpClients.createDefault();
//		HttpPost post = new HttpPost(url);
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
//		post.setConfig(requestConfig);
//		String result = null;
//		try
//		{
//			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
//					"utf-8");
//			post.setEntity(entity);
//			HttpResponse res = client.execute(post);
//			System.out.println(res.getStatusLine().getStatusCode());
//			System.out.println(HttpStatus.SC_OK);
//			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
//			{
//				byte[] byteArray = EntityUtils.toByteArray(res.getEntity());// 返回数据
//				result = new String(byteArray);
//			}
//		} catch (Exception e)
//		{
//			throw new RuntimeException(e);
//		}
//		return result;
//	}

	public static String urlEncodeUTF8(String source)
	{
		String result = source;
		try
		{
			result = new String(result.getBytes("iso-8859-1"), "utf-8");

		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}

//	/**
//	 * 测试方法
//	 *
//	 * @param args
//	 */
//	@SuppressWarnings({ "unused", "static-access", "unchecked", "rawtypes" })
//	public static void main(String[] args) throws Exception
//	{
//
//		String url = "http://robot.jmwyw.com/act";
//		Map params = new HashMap();
//		params.put("msg", "你好");
//
//		HttpClientUtil util = HttpClientUtil.getInstance("UTF-8");
//
//		String resultStr = util.getResponseBodyAsString(url, params);
//		byte[] resultArr = util.getResponseBodyAsByteArray(url, params);
//		/*
//		 * File file = new File("D:\\result.txt"); FileOutputStream out = new
//		 * FileOutputStream(file); util.getResponseBodyAsStream(url,params,out);
//		 */
//		System.out.println(resultStr);
//	}
}