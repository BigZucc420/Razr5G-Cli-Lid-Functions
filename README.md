# Razr5G Cli Lid Functions (xposed)
A module that helps the cli display's lid sensor work correctly on GSIs. It also aims to add functionality, but this has yet to be completed.

This module has been tested on the latest Android 12 phh GSI as of writing, but is not guaranteed to work on future or past GSIs.

What the module does:
- Hooks into sleepDisplayGroupNoUpdateLocked() in the PowerManagerService class to block the display from sleeping due to the lid switch, which would crash the entire system (more or less).

Bugs:
- the display will not sleep when the lid is closed. This is better than the system crashing, but is still not ideal. I will be trying to improve this.
- more that I'm probably missing...

Other things:
- the source code of Android is constantly evolving, so this module could stop working at any point in the future
- this module was tested using LSPosed (https://github.com/LSPosed/LSPosed)
- activate this module on "System Framework" then reboot
- this module is meant to be used in combination with these two magisk modules:
	- https://github.com/BigZucc420/Razr5G-Synaptics-Touchscreen-Device-Associations
	- https://github.com/BigZucc420/Razr5G-Foldable-Device-Fixes

Sources:
- Android Open Source Project (https://cs.android.com)
- Xposed Development Tutorial (https://github.com/rovo89/XposedBridge/wiki/Development-tutorial)
