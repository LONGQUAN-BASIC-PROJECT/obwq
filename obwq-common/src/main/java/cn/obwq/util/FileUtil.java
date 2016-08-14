package cn.obwq.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class FileUtil {
	/**
	 * 读取文本文件
	 * 
	 * @param file 支持text,html等文本文
	 * @return 返回文本文件内容
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	public static String readFile(File file) throws  Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
			// br = new BufferedReader(new FileReader(file));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String lineContent = null;
			while ((lineContent = br.readLine()) != null) {
				buffer.append(lineContent);
			}
		return buffer.toString();
	}

	/**
	 * 写入文本文件
	 * 
	 * @param file File对象
	 * @param content 文件内容
	 */
	public static void writeFile(File file, String content) {
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// FileWriter filewriter = null;
		Writer writer = null;
		try {
			file.createNewFile();

			writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			writer.write(content);

			// filewriter = new FileWriter(file, true);
			// filewriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				/*
				 * if (filewriter != null) { filewriter.close(); filewriter = null; }
				 */
				if (writer != null) {
					writer.close();
					writer = null;
				}
				dir = null;
				file = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * nio写文�?
	 * 
	 * @param file 写入的文件对
	 * @param content 写入的内
	 */
	public static void nioWwriteFile(File file, String content) {
		FileOutputStream fout = null;
		try {
			ByteBuffer in = ByteBuffer.wrap(content.getBytes("UTF-8"));
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			fout = new FileOutputStream(file);
			FileChannel fc = fout.getChannel();
			// in.flip();
			fc.write(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			file = null;
		}
	}

	/**
	 * 读取某个url下页面的内容
	 * @param URL 指定的url地址
	 */
	public void readHTML(String URL) {
		Charset charset = Charset.forName("GBK");// 创建GBK字符
		SocketChannel channel = null;
		try {
			InetSocketAddress socketAddress = new InetSocketAddress("www.baidu.com", 80);// step1:打开连接
			channel = SocketChannel.open(socketAddress);
			// step2:发请求，使用GBK编码
			channel.write(charset.encode("GET " + "/ HTTP/1.1" + "\r\n\r\n"));
			// step3:读取数据
			ByteBuffer buffer = ByteBuffer.allocate(1024);// 创建1024字节的缓
			while (channel.read(buffer) != -1) {
				buffer.flip();// flip方法在读缓冲区字节操作之前调用
				System.out.println(charset.decode(buffer));
				// 使用Charset.decode方法将字节转换为字符
				buffer.clear();// 清空缓冲
			}
		} catch (IOException e) {
			System.err.println(e.toString());
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
