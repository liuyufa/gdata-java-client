/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.model.gd;

import com.google.gdata.model.AttributeKey;
import com.google.gdata.model.DefaultRegistry;
import com.google.gdata.model.Element;
import com.google.gdata.model.ElementCreator;
import com.google.gdata.model.ElementKey;
import com.google.gdata.model.QName;
import com.google.gdata.util.Namespaces;

/**
 * Person's additional (often: middle) name.
 *
 * 
 */
public class AdditionalName extends Element {

  /**
   * The key for this element.
   */
  public static final ElementKey<String,
      AdditionalName> KEY = ElementKey.of(new QName(Namespaces.gNs,
      "additionalName"), String.class, AdditionalName.class);

  /**
   * Pronunciation help.
   */
  public static final AttributeKey<String> YOMI = AttributeKey.of(new
      QName(null, "yomi"), String.class);

  /*
   * Generate the default metadata for this element.
   */
  static {
    ElementCreator builder = DefaultRegistry.build(KEY);
    builder.addAttribute(YOMI);
  }

  /**
   * Default mutable constructor.
   */
  public AdditionalName() {
    this(KEY);
  }

  /**
   * Create an instance using a different key.
   */
  public AdditionalName(ElementKey<String, ? extends AdditionalName> key) {
    super(key);
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link Element} instance. Will use the given {@link ElementKey} as the key
   * for the element.
   *
   * @param key The key to use for this element.
   * @param source source element
   */
  public AdditionalName(ElementKey<String, ? extends AdditionalName> key,
      Element source) {
    super(key, source);
  }

  /**
   * Constructs a new instance with the given value.
   *
   * @param value value.
   */
  public AdditionalName(String value) {
    this();
    setValue(value);
  }

   @Override
   public AdditionalName lock() {
     return (AdditionalName) super.lock();
   }

  /**
   * Returns the value.
   *
   * @return value
   */
  public String getValue() {
    return super.getTextValue(KEY);
  }

  /**
   * Sets the value.
   *
   * @param value value or <code>null</code> to reset
   */
  public AdditionalName setValue(String value) {
    super.setTextValue(value);
    return this;
  }

  /**
   * Returns whether it has the value.
   *
   * @return whether it has the value
   */
  public boolean hasValue() {
    return super.hasTextValue();
  }

  /**
   * Returns the pronunciation help.
   *
   * @return pronunciation help
   */
  public String getYomi() {
    return super.getAttributeValue(YOMI);
  }

  /**
   * Sets the pronunciation help.
   *
   * @param yomi pronunciation help or <code>null</code> to reset
   */
  public AdditionalName setYomi(String yomi) {
    super.setAttributeValue(YOMI, yomi);
    return this;
  }

  /**
   * Returns whether it has the pronunciation help.
   *
   * @return whether it has the pronunciation help
   */
  public boolean hasYomi() {
    return getYomi() != null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!sameClassAs(obj)) {
      return false;
    }
    AdditionalName other = (AdditionalName) obj;
    return eq(getValue(), other.getValue())
        && eq(getYomi(), other.getYomi());
  }

  @Override
  public int hashCode() {
    int result = getClass().hashCode();
    if (getValue() != null) {
      result = 37 * result + getValue().hashCode();
    }
    if (getYomi() != null) {
      result = 37 * result + getYomi().hashCode();
    }
    return result;
  }

  @Override
  public String toString() {
    return "{AdditionalName value=" + getTextValue() + " yomi=" +
        getAttributeValue(YOMI) + "}";
  }

}