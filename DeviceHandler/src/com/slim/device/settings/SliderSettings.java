/*
 * Copyright (C) 2016 The CyanogenMod Project
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

package com.slim.device.settings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

import com.slim.device.KernelControl;
import com.slim.device.R;
import com.slim.device.util.FileUtils;
import com.slim.device.util.SliderUtils;
import com.slim.device.KeyHandler;

public class SliderSettings extends PreferenceActivity
        implements OnPreferenceChangeListener {

    private ListPreference mSliderTop;
    private ListPreference mSliderMiddle;
    private ListPreference mSliderBottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.slider_panel);

        mSliderTop = (ListPreference) findPreference("keycode_top_position");
        mSliderTop.setOnPreferenceChangeListener(this);

        mSliderMiddle = (ListPreference) findPreference("keycode_middle_position");
        mSliderMiddle.setOnPreferenceChangeListener(this);

        mSliderBottom = (ListPreference) findPreference("keycode_bottom_position");
        mSliderBottom.setOnPreferenceChangeListener(this);
    }

    private void setSummary(ListPreference preference, String value) {
            preference.setSummary(value);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String file;
        if (preference == mSliderTop) {
            SliderUtils.setSliderAction(SliderUtils.SLIDER_TOP, (String) newValue);
        } else if (preference == mSliderMiddle) {
            SliderUtils.setSliderAction(SliderUtils.SLIDER_MIDDLE, (String) newValue);
        } else if (preference == mSliderBottom) {
            SliderUtils.setSliderAction(SliderUtils.SLIDER_BOTTOM, (String) newValue);
        } else {
            return false;
        }
        setSummary((ListPreference) preference, (String) newValue);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Remove padding around the listview
            getListView().setPadding(0, 0, 0, 0);

        setSummary(mSliderTop, SliderUtils.getSliderAction(SliderUtils.SLIDER_TOP));
        setSummary(mSliderMiddle, SliderUtils.getSliderAction(SliderUtils.SLIDER_MIDDLE));
        setSummary(mSliderBottom, SliderUtils.getSliderAction(SliderUtils.SLIDER_BOTTOM));
    }
}
