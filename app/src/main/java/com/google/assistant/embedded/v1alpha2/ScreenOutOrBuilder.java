// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

public interface ScreenOutOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.assistant.embedded.v1alpha2.ScreenOut)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * *Output-only* The format of the provided screen data.
   * </pre>
   *
   * <code>optional .google.assistant.embedded.v1alpha2.ScreenOut.Format format = 1;</code>
   */
  int getFormatValue();
  /**
   * <pre>
   * *Output-only* The format of the provided screen data.
   * </pre>
   *
   * <code>optional .google.assistant.embedded.v1alpha2.ScreenOut.Format format = 1;</code>
   */
  com.google.assistant.embedded.v1alpha2.ScreenOut.Format getFormat();

  /**
   * <pre>
   * *Output-only* The raw screen data to be displayed as the result of the
   * Assistant query.
   * </pre>
   *
   * <code>optional bytes data = 2;</code>
   */
  com.google.protobuf.ByteString getData();
}
