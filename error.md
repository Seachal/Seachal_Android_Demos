

-----
# 1 
Execution failed for task ':seachaltest:dataBindingMergeDependencyArtifactsDebug'.
> Could not resolve all files for configuration ':seachaltest:debugCompileClasspath'.
> Failed to transform activity-1.8.0.aar (androidx.activity:activity:1.8.0) to match attributes {artifactType=android-databinding, org.gradle.category=library, org.gradle.dependency.bundling=external, org.gradle.libraryelements=aar, org.gradle.status=release, org.gradle.usage=java-api}.
> Execution failed for JetifyTransform: /Users/seachal/.gradle/caches/modules-2/files-2.1/androidx.activity/activity/1.8.0/4266e2118d565daa20212d1726e11f41e1a4d0ca/activity-1.8.0.aar.
> Failed to transform '/Users/seachal/.gradle/caches/modules-2/files-2.1/androidx.activity/activity/1.8.0/4266e2118d565daa20212d1726e11f41e1a4d0ca/activity-1.8.0.aar' using Jetifier. Reason: Unsupported class file major version 61. (Run with --stacktrace for more details.)
> Could not download activity-1.8.0.aar (androidx.activity:activity:1.8.0): No cached version available for offline mode


FAILURE: Build completed with 7 failures.

1: Task failed with an exception.
-----------
* What went wrong:
  Cannot isolate parameters com.android.build.gradle.internal.dependency.AarResourcesCompilerTransform$Parameters_Decorated@5f62062c of artifact transform AarResourcesCompilerTransform
> Could not isolate value com.android.build.gradle.internal.dependency.AarResourcesCompilerTransform$Parameters_Decorated@5f62062c of type AarResourcesCompilerTransform.Parameters
> Could not resolve all files for configuration ':seachaltest:_internal_aapt2_binary'.
> Could not download aapt2-3.6.3-6040484-osx.jar (com.android.tools.build:aapt2:3.6.3-6040484): No cached version available for offline mode



Cannot isolate parameters com.android.build.gradle.internal.dependency.AarResourcesCompilerTransform$Parameters_Decorated@5d0c4832 of artifact transform AarResourcesCompilerTransform
> Could not isolate value com.android.build.gradle.internal.dependency.AarResourcesCompilerTransform$Parameters_Decorated@5d0c4832 of type AarResourcesCompilerTransform.Parameters
> Could not resolve all files for configuration ':seachaltest:_internal_aapt2_binary'.
> Could not download aapt2-3.6.3-6040484-osx.jar (com.android.tools.build:aapt2:3.6.3-6040484): No cached version available for offline mode
>

原因是 

![](.error_images/d8a6ef2b.png)

原因是 gradle  被设置成了离线模式，修改成在线模式就好了。 