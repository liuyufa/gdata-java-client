/*
 * Copyright (c) 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.http;

import java.io.IOException;

/**
 * Authorization header provider for HTTP requests. This is primarily used for
 * signing HTTP requests such as used for example for OAuth.
 */
public interface AuthorizationHeaderProvider {

  /**
   * Returns the {@code Authorization} header to use for a request of the given
   * method and URL.
   * <p>
   * Called for every request right before the request is executed in
   * {@link HttpRequest#execute()}. Any existing header value for the {@code
   * Authorization} key of the request will be overriden by the result of this
   * method. The authorization header value will not be logged.
   */
  String getAuthorizationHeader(String requestMethod, String requestUrl)
      throws IOException;
}