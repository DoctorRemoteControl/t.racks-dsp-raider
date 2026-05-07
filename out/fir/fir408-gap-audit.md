# FIR408 Gap Audit

- Library: DspLib-408-fir.json
- Status entries scanned: 138
- Entries needing work: 61

## Status Counts

| Status | Count |
| --- | ---: |
| predicted_from_ui_order | 10 |
| not_decoded | 5 |
| candidate_static_storage | 5 |
| unknown | 5 |
| predicted_from_dsp408_static_layout | 4 |
| partially_decoded | 4 |
| observed_value_unknown_meaning | 2 |
| decoded_with_predicted_bypass_and_external_fir_endpoints | 2 |
| observed_plus_inferred_strong | 2 |
| decoded_with_predicted_intermediate_window_values | 2 |
| predicted_from_contiguous_input_map | 2 |
| not_yet_compared_after_each_0x4B_field | 1 |
| not_observed_in_cmd56_selector_0_offsets | 1 |
| predicted_from_dsp408 | 1 |
| observed_raw_predicted_name_from_ui_order | 1 |
| predicted_from_capture_and_ui_order | 1 |
| observed_raw_during_highpass_lowpass_captures_predicted_name_from_ui_order | 1 |
| not_observed_in_0x27_config_dump | 1 |
| predicted_from_user_window_change_and_ui_order | 1 |
| hypothesis | 1 |
| partially_answered_mode_write_decoded_for_out1 | 1 |
| candidate_decode_implemented | 1 |
| partially_answered_low_offsets_are_little_endian_float32_coefficients_or_coefficient_preview | 1 |
| open | 1 |
| partially_answered_selector_0_likely_ina | 1 |
| predicted_from_dsp408_static_layout_and_matches_default_frequencies | 1 |
| partially_confirmed_input_selector_map | 1 |
| ui_observed_not_protocol_decoded | 1 |
| predicted_from_dsp408_static_layout_and_default_dump | 1 |

## Work Items

| Path | Status | Suggested next action |
| --- | --- | --- |
| $.channels.record_layout_in_config_dump.input_records.terminal_u16le_candidate | observed_value_unknown_meaning | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.channels.record_layout_in_config_dump.output_records.terminal_u16le_candidate | observed_value_unknown_meaning | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x11.decode_status | unknown | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x12.decode_status | unknown | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x14.decode_status | unknown | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x22.decode_status | unknown | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x2C.decode_status | unknown | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.commands.0x4B.type_enum_observed_or_predicted.0x00 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.type_enum_observed_or_predicted.0x03 | observed_raw_during_highpass_lowpass_captures_predicted_name_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.type_enum_observed_or_predicted.0x04 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.window_enum_partial.0x04 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.window_enum_partial.0x05 | predicted_from_user_window_change_and_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.window_enum_partial.0x07 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4B.window_enum_partial.0x08 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.commands.0x4E.observed_external_impulse_upload.selector_observations.InC | predicted_from_contiguous_input_map | active FIR upload/read probe; GUI only if direct upload differs |
| $.commands.0x4E.observed_external_impulse_upload.selector_observations.InD | predicted_from_contiguous_input_map | active FIR upload/read probe; GUI only if direct upload differs |
| $.commands.0x4F.decode_status | partially_decoded | active FIR upload/read probe; GUI only if direct upload differs |
| $.commands.0x56.decode_status | hypothesis | active FIR upload/read probe; GUI only if direct upload differs |
| $.fir_specific_ui_controls | ui_observed_not_protocol_decoded | review and promote when evidence is sufficient |
| $.open_questions[0] | partially_answered_selector_0_likely_ina | review and promote when evidence is sufficient |
| $.open_questions[1] | partially_answered_low_offsets_are_little_endian_float32_coefficients_or_coefficient_preview | review and promote when evidence is sufficient |
| $.open_questions[2] | open | inspect log/static dump first; GUI capture only if no command/read path exists |
| $.open_questions[3] | partially_answered_mode_write_decoded_for_out1 | review and promote when evidence is sufficient |
| $.parameters.config_dump.output_record_static_decode | candidate_decode_implemented | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.fir_highpass_frequency_u16le | candidate_static_storage | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.fir_lowpass_frequency_u16le | candidate_static_storage | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.fir_taps_raw_u16le | candidate_static_storage | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.fir_type_u8 | candidate_static_storage | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.fir_window_u8 | candidate_static_storage | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.iir_highpass_frequency_u16le | predicted_from_dsp408_static_layout | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.iir_highpass_mode_u8 | predicted_from_dsp408_static_layout | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.iir_lowpass_frequency_u16le | predicted_from_dsp408_static_layout | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.iir_lowpass_mode_u8 | predicted_from_dsp408_static_layout | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.output_gain_u16le | predicted_from_dsp408_static_layout_and_default_dump | direct 0x4B write plus 0x27 config readback |
| $.parameters.config_dump.output_record_static_decode.fields.output_peq_records | predicted_from_dsp408_static_layout_and_matches_default_frequencies | direct 0x4B write plus 0x27 config readback |
| $.parameters.external_fir_upload.readback_via_cmd56 | partially_decoded | active FIR upload/read probe; GUI only if direct upload differs |
| $.parameters.external_fir_upload.selector_interpretation | partially_confirmed_input_selector_map | active FIR upload/read probe; GUI only if direct upload differs |
| $.parameters.fir_chunk_read | partially_decoded | review and promote when evidence is sufficient |
| $.parameters.fir_chunk_read.data_field | partially_decoded | review and promote when evidence is sufficient |
| $.parameters.fir_filter_type | decoded_with_predicted_bypass_and_external_fir_endpoints | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_filter_type.read_storage | not_decoded | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_filter_type.write | decoded_with_predicted_bypass_and_external_fir_endpoints | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_filter_type.write.enum_partial.0x00 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_filter_type.write.enum_partial.0x03 | observed_raw_predicted_name_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_filter_type.write.enum_partial.0x04 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_generator.read_storage | not_yet_compared_after_each_0x4B_field | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_highpass_frequency.read_storage | not_decoded | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_lowpass_frequency.read_storage | not_decoded | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_ok_apply.read_storage | not_observed_in_cmd56_selector_0_offsets | review and promote when evidence is sufficient |
| $.parameters.fir_processing_mode.read_storage | not_observed_in_0x27_config_dump | live command endpoint check; config dump will not prove it |
| $.parameters.fir_tap_count.read_storage | not_decoded | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function | decoded_with_predicted_intermediate_window_values | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.read_storage | not_decoded | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.write | decoded_with_predicted_intermediate_window_values | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.write.enum_partial.0x04 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.write.enum_partial.0x05 | predicted_from_capture_and_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.write.enum_partial.0x07 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.parameters.fir_window_function.write.enum_partial.0x08 | predicted_from_ui_order | direct 0x4B write plus 0x27 config readback |
| $.value_models.fir_tap_count | observed_plus_inferred_strong | direct 0x4B write plus 0x27 config readback |
| $.value_models.gain | predicted_from_dsp408 | scripted endpoint write/read diff, then mark inherited_confirmed |
| $.value_models.peq_frequency | observed_plus_inferred_strong | review and promote when evidence is sufficient |
