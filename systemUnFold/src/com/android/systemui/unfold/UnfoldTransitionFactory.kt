/*
 * Copyright (C) 2021 The Android Open Source Project
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
@file:JvmName("UnfoldTransitionFactory")

package com.android.systemui.unfold

import android.content.Context
import android.hardware.SensorManager
import android.hardware.display.DisplayManager
import android.os.Handler
import com.android.systemui.unfold.config.UnfoldTransitionConfig
import com.android.systemui.unfold.updates.FoldProvider
import com.android.systemui.unfold.updates.screen.ScreenStatusProvider
import com.android.systemui.unfold.util.CurrentActivityTypeProvider
import java.util.concurrent.Executor

/**
 * Factory for [UnfoldSharedComponent].
 *
 * This wraps the autogenerated factory (for discoverability), and is needed as Launcher has to
 * create the object manually. If dagger is available, this object is provided in
 * [UnfoldSharedModule].
 *
 * This should **never** be called from sysui, as the object is already provided in that process.
 */
fun createUnfoldSharedComponent(
        context: Context,
        config: UnfoldTransitionConfig,
        screenStatusProvider: ScreenStatusProvider,
        foldProvider: FoldProvider,
        activityTypeProvider: CurrentActivityTypeProvider,
        sensorManager: SensorManager,
        mainHandler: Handler,
        mainExecutor: Executor,
        singleThreadBgExecutor: Executor,
        tracingTagPrefix: String,
        displayManager: DisplayManager,
): UnfoldSharedComponent =
        DaggerUnfoldSharedComponent.factory()
                .create(
                        context,
                        config,
                        screenStatusProvider,
                        foldProvider,
                        activityTypeProvider,
                        sensorManager,
                        mainHandler,
                        mainExecutor,
                        singleThreadBgExecutor,
                        tracingTagPrefix,
                        displayManager,
                )

/**
 * Factory for [RemoteUnfoldSharedComponent].
 *
 * Wraps [DaggerRemoteUnfoldSharedComponent] (that is autogenerated), for better discoverability.
 */
fun createRemoteUnfoldSharedComponent(
        context: Context,
        config: UnfoldTransitionConfig,
        mainExecutor: Executor,
        mainHandler: Handler,
        singleThreadBgExecutor: Executor,
        tracingTagPrefix: String,
        displayManager: DisplayManager,
        ): RemoteUnfoldSharedComponent =
        DaggerRemoteUnfoldSharedComponent.factory()
                .create(
                        context,
                        config,
                        mainExecutor,
                        mainHandler,
                        singleThreadBgExecutor,
                        displayManager,
                        tracingTagPrefix,
                )
