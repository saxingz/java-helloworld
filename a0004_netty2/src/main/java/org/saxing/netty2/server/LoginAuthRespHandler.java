package org.saxing.netty2.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import org.saxing.netty2.MessageType;
import org.saxing.netty2.ResultType;
import org.saxing.netty2.struct.NettyMessage;
import org.saxing.netty2.struct.NettyMessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthRespHandler.class);
	/**
	 * 考虑到安全，链路的建立需要通过基于IP地址或者号段的黑白名单安全认证机制，本例中，多个IP通过逗号隔开
	 */
	private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
	private String[] whitekList = { "127.0.0.1", "192.168.56.1" };

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward to
	 * the next {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;

		// 如果是握手请求消息，处理，其它消息透传
		if (message.getNettyMessageHeader() != null && message.getNettyMessageHeader().getType() == MessageType.LOGIN_REQ.value()) {
			String nodeIndex = ctx.channel().remoteAddress().toString();
			NettyMessage loginResp = null;
			// 重复登陆，拒绝
			if (nodeCheck.containsKey(nodeIndex)) {
				LOGGER.error("重复登录,拒绝请求!");
				loginResp = buildResponse(ResultType.FAIL);
			} else {
				InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
				String ip = address.getAddress().getHostAddress();
				boolean isOK = false;
				for (String wip : whitekList) {
					if (wip.equals(ip)) {
						isOK = true;
						break;
					}
				}
				loginResp = isOK ? buildResponse(ResultType.SUCCESS) : buildResponse(ResultType.FAIL);
				if (isOK) {
					nodeCheck.put(nodeIndex, true);
				}
			}
			LOGGER.info("The login response is : {} body [{}]",loginResp,loginResp.getBody());
			ctx.writeAndFlush(loginResp);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	/**
	 * 服务端接到客户端的握手请求消息后，如果IP校验通过，返回握手成功应答消息给客户端，应用层成功建立链路，否则返回验证失败信息。消息格式如下：
	 * 1.消息头的type为4
	 * 2.可选附件个数为0
	 * 3.消息体为byte类型的结果，0表示认证成功，1表示认证失败
	 * @see ResultType
	 * @param result
	 * @return
	 */
	private NettyMessage buildResponse(ResultType result) {
		NettyMessage message = new NettyMessage();
		NettyMessageHeader nettyMessageHeader = new NettyMessageHeader();
		nettyMessageHeader.setType(MessageType.LOGIN_RESP.value());
		message.setNettyMessageHeader(nettyMessageHeader);
		message.setBody(new byte[]{result.value()});
		return message;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		nodeCheck.remove(ctx.channel().remoteAddress().toString());// 删除缓存
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
}
