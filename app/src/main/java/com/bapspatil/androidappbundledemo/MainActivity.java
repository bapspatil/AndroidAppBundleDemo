
/*
 * Copyright 2018 Bapusaheb Patil
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

package com.bapspatil.androidappbundledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private SplitInstallManager splitInstallManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate an instance of SplitInstallManager for the dynamic feature module
        splitInstallManager =
                SplitInstallManagerFactory.create(getApplicationContext());

        Button buttonFeatureOne = findViewById(R.id.btn_feature_one);
        Button buttonFeatureTwo = findViewById(R.id.btn_feature_two);

        buttonFeatureOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFeatureOne();
            }
        });
        buttonFeatureTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFeatureTwo();
            }
        });
    }

    public void loadFeatureOne() {
        // Builds a request to install the feature1 module
        SplitInstallRequest request =
                SplitInstallRequest
                        .newBuilder()
                        // You can download multiple on demand modules per
                        // request by invoking the following method for each
                        // module you want to install.
                        .addModule("feature1")
                        .build();

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                .startInstall(request)
                .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        // Module download successful
                        Intent intent = new Intent().setClassName(getPackageName(), "com.bapspatil.feature1.FeatureOneActivity");
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Module download failed; handle the error here
                        Toast.makeText(getApplicationContext(), "Couldn't download feature1: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void loadFeatureTwo() {
        // Builds a request to install the feature1 module
        SplitInstallRequest request =
                SplitInstallRequest
                        .newBuilder()
                        // You can download multiple on demand modules per
                        // request by invoking the following method for each
                        // module you want to install.
                        .addModule("feature2")
                        .build();

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                .startInstall(request)
                .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        // Module download successful
                        Intent intent = new Intent().setClassName(getPackageName(), "com.bapspatil.feature2.FeatureTwoActivity");
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Module download failed; handle the error here
                        Toast.makeText(getApplicationContext(), "Couldn't download feature2: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
