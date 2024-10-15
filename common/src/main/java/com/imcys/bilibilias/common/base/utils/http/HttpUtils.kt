package com.imcys.bilibilias.common.base.utils.http

import com.imcys.bilibilias.common.base.constant.BILIBILI_URL
import com.imcys.bilibilias.common.base.constant.BROWSER_USER_AGENT
import com.imcys.bilibilias.common.base.constant.COOKIE
import com.imcys.bilibilias.common.base.constant.REFERER
import com.imcys.bilibilias.common.base.constant.USER_AGENT
import com.imcys.bilibilias.common.base.extend.awaitResponse
import kotlinx.coroutines.*
import okhttp3.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author imcys
 *
 * 此类为okhttp3的封装类
 */
@Deprecated("ktor")
object HttpUtils {

    private val okHttpClient = OkHttpClient()

    private val headers = mutableMapOf<String, String>()

    /**
     * 使用 OkHttp 库发送 GET 请求的方法
     *
     * @param url String 请求地址
     * @param callBack Callback 请求完成后的回调函数
     */
    @JvmStatic
    @Deprecated("ktor")
    fun get(url: String, callBack: Callback) {
        // 检验url，添加对应的ua
        checkUrl()
        // 创建请求对象
        val request: Request = Request.Builder().apply {
            // 将已设置的头信息添加到请求中
            headers.forEach {
                addHeader(it.key, it.value)
            }
            // 设置请求地址
            url(url)
            // 设置为 GET 请求
            get()
        }.build()
        // 使用 OkHttp 的 enqueue 方法异步发送请求
        okHttpClient.newCall(request).enqueue(callBack)
    }

    /**
     * 使用 OkHttp 库发送 GET 请求的方法
     *
     * @param url String 请求地址
     * @param callBack Callback 请求完成后的回调函数
     */
    @JvmStatic
    @Deprecated("ktor")
    fun asyncGet(url: String): Deferred<Response> {
        // 检验url，添加对应的ua
        checkUrl()
        // 创建请求对象
        val request: Request = Request.Builder().apply {
            // 将已设置的头信息添加到请求中
            headers.forEach {
                addHeader(it.key, it.value)
            }
            // 设置请求地址
            url(url)
            // 设置为 GET 请求
            get()
        }.build()
        // 使用 OkHttp 的 enqueue 方法异步发送请求
        return CoroutineScope(Dispatchers.Default).async {
            okHttpClient.newCall(request).awaitResponse()
        }
    }

    /**
     * 添加请求头
     *
     * @param key String
     * @param value String
     * @return HttpUtils
     */
    @JvmStatic
    fun addHeader(key: String, value: String): HttpUtils {
        headers[key] = value
        return this
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    fun doCardPost(url: String?, param: String?, Cookie: String?): String? {
        var out: PrintWriter? = null
        var `in`: BufferedReader? = null
        var result: String? = ""
        try {
            val realUrl = URL(url)
            // 打开和URL之间的连接
            val conn: HttpURLConnection = realUrl
                .openConnection() as HttpURLConnection
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*")
            conn.setRequestProperty("connection", "Keep-Alive")
            conn.requestMethod = "POST"
            conn.setRequestProperty(REFERER, "$BILIBILI_URL/")
            conn.setRequestProperty(
                USER_AGENT,
                BROWSER_USER_AGENT
            )
            conn.setRequestProperty(COOKIE, Cookie)
            conn.setRequestProperty("charset", "utf-8")
            conn.useCaches = false
            // 发送POST请求必须设置如下两行
            conn.doOutput = true
            conn.doInput = true
            conn.readTimeout = 5000
            conn.connectTimeout = 5000
            if (param != null && param.trim { it <= ' ' } != "") {
                // 获取URLConnection对象对应的输出流
                out = PrintWriter(conn.outputStream)
                // 发送请求参数
                out.print(param)
                // flush输出流的缓冲
                out.flush()
            }

            // 定义BufferedReader输入流来读取URL的响应
            `in` = BufferedReader(
                InputStreamReader(conn.inputStream)
            )
            var line: String?
            while (`in`.readLine().also { line = it } != null) {
                result += line
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } // 使用finally块来关闭输出流、输入流
        finally {
            try {
                out?.close()
                `in`?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        return result
    }

    private fun checkUrl() {
        headers[USER_AGENT] = BROWSER_USER_AGENT
    }
}
