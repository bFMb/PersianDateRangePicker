/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.github.bkhezry.persiandaterangepicker.date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ViewAnimator;

import com.github.bkhezry.persiandaterangepicker.persiandateutils.LanguageUtils;
import com.github.bkhezry.persiandaterangepicker.persiandateutils.PersianDate;

public class PersianAccessibleDateAnimator extends ViewAnimator {
  private long mDateMillis;

  public PersianAccessibleDateAnimator(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setDateMillis(long dateMillis) {
    mDateMillis = dateMillis;
  }

  /**
   * Announce the currently-selected date when launched.
   */
  @Override
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
    if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
      // Clear the event's current text so that only the current date will be spoken.
      event.getText().clear();
      PersianDate mPersianDate = new PersianDate();
      mPersianDate.setTimeInMillis(mDateMillis);
      String dateString = LanguageUtils.getPersianNumbers(
        mPersianDate.getPersianMonth() + " " +
          mPersianDate.getPersianDay()
      );
      event.getText().add(dateString);
      return true;
    }
    return super.dispatchPopulateAccessibilityEvent(event);
  }
}