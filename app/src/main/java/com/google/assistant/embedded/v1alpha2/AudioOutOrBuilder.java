// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/assistant/embedded/v1alpha2/embedded_assistant.proto

package com.google.assistant.embedded.v1alpha2;

public interface AudioOutOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.assistant.embedded.v1alpha2.AudioOut)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * *Output-only* The audio data containing the Assistant's response to the
   * query. Sequential chunks of audio data are received in sequential
   * `AssistResponse` messages.
   * </pre>
   *
   * <code>optional bytes audio_data = 1;</code>
   */
  com.google.protobuf.ByteString getAudioData();
}
