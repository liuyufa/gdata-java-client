// Copyright 2010 Google Inc. All Rights Reserved.

package com.google.api.data.client.v2.jsonc.jackson;

import com.google.api.data.client.v2.DateTime;
import com.google.api.data.client.v2.GDataSerializer;
import com.google.api.data.client.v2.ClassInfo;
import com.google.api.data.client.v2.jsonc.JsoncObject;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

class JsoncSerializer implements GDataSerializer {
  // TODO: ability to annotate fields as not needed, or only needed for
  // POST?

  final Object item;

  JsoncSerializer(Object item) {
    this.item = item;
  }

  public final long getContentLength() {
    // TODO
    return -1;
  }

  public final void serialize(OutputStream out) throws IOException {
    JsonGenerator generator =
        Jackson.JSON_FACTORY.createJsonGenerator(out, JsonEncoding.UTF8);
    generator.writeStartObject();
    generator.writeFieldName("data");
    serializeData(generator);
    generator.close();
  }

  void serializeData(JsonGenerator generator) throws IOException {
    serialize(generator, this.item);
  }

  static void serialize(JsonGenerator generator, Object value)
      throws IOException {
    if (value == null) {
      generator.writeNull();
    }
    if (value instanceof String || value instanceof Long
        || value instanceof Double || value instanceof BigInteger
        || value instanceof BigDecimal) {
      // TODO: double: what about +- infinity?
      generator.writeString(value.toString());
    } else if (value instanceof Boolean) {
      generator.writeBoolean((Boolean) value);
    } else if (value instanceof Integer || value instanceof Short
        || value instanceof Byte) {
      generator.writeNumber(((Number) value).intValue());
    } else if (value instanceof Float) {
      // TODO: what about +- infinity?
      generator.writeNumber((Float) value);
    } else if (value instanceof DateTime) {
      generator.writeString(((DateTime) value).toStringRfc3339());
    } else if (value instanceof List<?>) {
      generator.writeStartArray();
      @SuppressWarnings("unchecked")
      List<Object> listValue = (List<Object>) value;
      int size = listValue.size();
      for (int i = 0; i < size; i++) {
        serialize(generator, listValue.get(i));
      }
      generator.writeEndArray();
    } else {
      generator.writeStartObject();
      if (!(value instanceof Map<?, ?>)) {
        ClassInfo typeInfo = ClassInfo.of(value.getClass());
        for (String name : typeInfo.getNames()) {
          Field field = typeInfo.getField(name);
          Object fieldValue = ClassInfo.getValue(field, value);
          if (fieldValue != null) {
            serializeField(generator, name, fieldValue);
          }
        }
        if (value instanceof JsoncObject) {
          value = ((JsoncObject) value).getUnknownKeyMap();
        }
      }
      if (value instanceof Map<?, ?>) {
        @SuppressWarnings("unchecked")
        Map<String, Object> mapValue = (Map<String, Object>) value;
        for (Map.Entry<String, Object> entry : mapValue.entrySet()) {
          serializeField(generator, entry.getKey(), entry.getValue());
        }
      }
      generator.writeEndObject();
    }
  }

  private static void serializeField(JsonGenerator generator, String fieldName,
      Object fieldValue) throws IOException {
    generator.writeFieldName(fieldName);
    serialize(generator, fieldValue);

  }
}
