/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.example.tongyi.service.impl.helloworld;

import java.util.Map;

import com.alibaba.cloud.ai.example.tongyi.service.AbstractTongYiServiceImpl;
import com.alibaba.cloud.ai.example.tongyi.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.StreamingChatClient;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple example.
 * There is optional message parameter whose default value is "Tell me a joke". pl The response to the request is from the TongYi models Service.
 *
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 * @since 2023.0.0.0-RC1
 */

@Service
@Slf4j
public class TongYiSimpleServiceImpl extends AbstractTongYiServiceImpl {
	/**
	 * 自动注入ChatClient、StreamingChatClient，屏蔽模型调用细节
	 */
	private final ChatClient chatClient;

	private final StreamingChatClient streamingChatClient;

	@Autowired
	public TongYiSimpleServiceImpl(ChatClient chatClient, StreamingChatClient streamingChatClient) {
		this.chatClient = chatClient;
		this.streamingChatClient = streamingChatClient;
	}
	/**
	 * 具体实现：
	 */
	@Override
	public String completion(String message) {
		Prompt prompt = new Prompt(new UserMessage(message));
		return chatClient.call(prompt).getResult().getOutput().getContent();
	}
}