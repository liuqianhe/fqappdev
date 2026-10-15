package com.fqapp.fqapp

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterShellArgs

class MainActivity : FlutterActivity() {
    override fun getFlutterShellArgs(): FlutterShellArgs = super.getFlutterShellArgs().also {
        // Runs before the engine/FlutterLoader starts; configureFlutterEngine is
        // too late to select the process-wide rendering backend.
        RendererCompatibility.configure(it)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // Native ExoPlayer host for DRM short dramas (CENC streaming decrypt).
        flutterEngine.plugins.add(NativePlayerPlugin())
        flutterEngine.plugins.add(ReaderDevicePlugin())
        flutterEngine.plugins.add(SystemTtsPlugin())
        flutterEngine.plugins.add(LocalBookPickerPlugin())
        flutterEngine.plugins.add(LibraryBackupPlugin())
        // 官方系统分享是 Intent.ACTION_SEND；没有可分享的应用时回 false。
        flutterEngine.plugins.add(SharePlugin())
        // 整本 TXT 导出：MediaStore.Downloads 或应用外部目录，见 DownloadsPlugin。
        flutterEngine.plugins.add(DownloadsPlugin())
        // 海信墨水屏整屏刷新（压残影）：反射调用厂商 EPD 接口。
        flutterEngine.plugins.add(EinkRefreshPlugin())
    }
}
