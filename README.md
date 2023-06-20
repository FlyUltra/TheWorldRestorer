# TheWorldRestorer
![badge](https://img.shields.io/github/downloads/FlyUltra/TheWorldRestorer/total)
![badge](https://img.shields.io/github/last-commit/FlyUltra/TheWorldRestorer)
![badge](https://img.shields.io/badge/platform-spigot-lightgrey)
[![badge](https://img.shields.io/github/license/FlyUltra/TheWorldRestorer)](https://github.com/FlyUltra/TheWorldRestorer/blob/master/LICENSE.txt)

Plugin for restoring the world (API)

API will make backup folder in your plugin
And in that folder you store your backups
When you start need your backup you only call one method for it

## Table of contents

* [Example](#example)
* [License](#license)

## Example

```java
WorldRestorer worldR = new WorldRestorer(JavaPlugin plugin, String world); // new instance for the class with that world
worldR.backupWorld(); // This save the world to backUp folder
worldR.restoreWorld(); // This returns the world from backUp
worldR.deleteWorld(File worldFolder); // This delete the world with folder
```

### License
TheWorldRestorer is licensed under the permissive license. Please see [`LICENSE.txt`](https://github.com/FlyUltra/TheWorldRestorer/blob/master/LICENSE.txt) for more information.