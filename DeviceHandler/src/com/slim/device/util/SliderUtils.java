/*
 * Copyright (C) 2013 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slim.device.util;

import android.util.Log;
import android.util.SparseArray;

public class SliderUtils {

    private static final String TAG = "SliderUtils";

    // Slider
    public static final int SLIDER_TOP = 601;
    public static final int SLIDER_MIDDLE = 602;
    public static final int SLIDER_BOTTOM = 603;

    public static final String mode_total_silence = "MODE_TOTAL_SILENCE";
    public static final String mode_alarms_only = "MODE_ALARMS_ONLY";
    public static final String mode_priority_only = "MODE_PRIORITY_ONLY";
    public static final String mode_all_notifications = "MODE_ALL_NOTIFICATIONS";
    public static final String mode_vibrate = "MODE_VIBRATE";
    public static final String mode_ring = "MODE_RING";

    private static SparseArray<String> sliderActions = new SparseArray<>();;

    private SliderUtils() {
        // This class should not be initialized
    }

    public static void setSliderAction(int keycode, String action) {
        Log.d(TAG, "setSliderActiong: Inserting keycode: " + keycode + " with action of: " + action);
        sliderActions.put(keycode, action);
    }

    public static String getSliderAction(int keycode) {
        String value = "";
        switch (keycode) {
            case SLIDER_TOP: {
                value = mode_alarms_only;
                break;
            }
            case SLIDER_MIDDLE: {
                value = mode_priority_only;
                break;
            }
            case SLIDER_BOTTOM: {
                value = mode_all_notifications;
                break;
            }
        }
        Log.d(TAG, "gestSliderAction: Returning value: " + sliderActions.get(keycode, value) + " for Key Code: " + keycode);
        return sliderActions.get(keycode, value);
    }

}
