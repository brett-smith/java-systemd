/*
 * Java-systemd implementation
 * Copyright (c) 2016 Markus Enax
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of either the GNU Lesser General Public License Version 2 or the
 * Academic Free Licence Version 2.1.
 *
 * Full licence texts are included in the COPYING file with this program.
 */

package org.systemd;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.systemd.interfaces.ServiceInterface;
import org.systemd.types.BlockIOBandwidth;
import org.systemd.types.BlockIODeviceWeight;
import org.systemd.types.EnvironmentFile;
import org.systemd.types.ExecutionInfo;
import org.systemd.types.SELinuxContext;
import org.systemd.types.SystemCallFilter;

public class Service extends Unit {

    public static final String SERVICE_NAME = Systemd.SERVICE_NAME + ".Service";
    public static final String UNIT_SUFFIX = ".service";

    private final Properties properties;

    private Service(final DBusConnection dbus, final ServiceInterface iface, final String name) throws DBusException {
        super(dbus, iface, name);

        this.properties = Properties.create(dbus, iface.getObjectPath(), SERVICE_NAME);
    }

    static Service create(final DBusConnection dbus, String name) throws DBusException {
        name = Unit.normalizeName(name, UNIT_SUFFIX);

        String objectPath = Unit.OBJECT_PATH + Systemd.escapePath(name);
        ServiceInterface iface = dbus.getRemoteObject(Systemd.SERVICE_NAME, objectPath, ServiceInterface.class);

        return new Service(dbus, iface, name);
    }

    @Override
    public ServiceInterface getInterface() {
        return (ServiceInterface) super.getInterface();
    }

    public boolean getBlockIOAccounting() {
        return properties.getBoolean("BlockIOAccounting");
    }

    public List<BlockIODeviceWeight> getBlockIODeviceWeight() {
        return BlockIODeviceWeight.list(properties.getVector("BlockIODeviceWeight"));
    }

    public List<BlockIOBandwidth> getBlockIOReadBandwidth() {
        return BlockIOBandwidth.list(properties.getVector("BlockIOReadBandwidth"));
    }

    public BigInteger getBlockIOWeight() {
        return properties.getBigInteger("BlockIOWeight");
    }

    public List<BlockIOBandwidth> getBlockIOWriteBandwidth() {
        return BlockIOBandwidth.list(properties.getVector("BlockIOWriteBandwidth"));
    }

    public String getBusName() {
        return properties.getString("BusName");
    }

    public boolean getCPUAccounting() {
        return properties.getBoolean("CPUAccounting");
    }

    public BigInteger getCPUQuotaPerSecUSec() {
        return properties.getBigInteger("CPUQuotaPerSecUSec");
    }

    public int getCPUSchedulingPolicy() {
        return properties.getInteger("CPUSchedulingPolicy");
    }

    public int getCPUSchedulingPriority() {
        return properties.getInteger("CPUSchedulingPriority");
    }

    public boolean isCPUSchedulingResetOnFork() {
        return properties.getBoolean("CPUSchedulingResetOnFork");
    }

    public BigInteger getCPUShares() {
        return properties.getBigInteger("CPUShares");
    }

    public BigInteger getCPUUsageNSec() {
        return properties.getBigInteger("CPUUsageNSec");
    }

    public String getCapabilities() {
        return properties.getString("Capabilities");
    }

    public BigInteger getCapabilityBoundingSet() {
        return properties.getBigInteger("CapabilityBoundingSet");
    }

    public String getControlGroup() {
        return properties.getString("ControlGroup");
    }

    public long getControlPID() {
        return properties.getLong("ControlPID");
    }

    public boolean doesDelegate() {
        return properties.getBoolean("Delegate");
    }

    public String getDevicePolicy() {
        return properties.getString("DevicePolicy");
    }

    public Vector<String> getEnvironment() {
        return properties.getVector("Environment");
    }

    public List<EnvironmentFile> getEnvironmentFiles() {
        return EnvironmentFile.list(properties.getVector("EnvironmentFiles"));
    }

    public int getExecMainCode() {
        return properties.getInteger("ExecMainCode");
    }

    public long getExecMainExitTimestamp() {
        return properties.getLong("ExecMainExitTimestamp");
    }

    public long getExecMainExitTimestampMonotonic() {
        return properties.getLong("ExecMainExitTimestampMonotonic");
    }

    public long getExecMainPID() {
        return properties.getLong("ExecMainPID");
    }

    public long getExecMainStartTimestamp() {
        return properties.getLong("ExecMainStartTimestamp");
    }

    public long getExecMainStartTimestampMonotonic() {
        return properties.getLong("ExecMainStartTimestampMonotonic");
    }

    public int getExecMainStatus() {
        return properties.getInteger("ExecMainStatus");
    }

    public List<ExecutionInfo> getExecReload() {
        return ExecutionInfo.list(properties.getVector("ExecReload"));
    }

    public List<ExecutionInfo> getExecStart() {
        return ExecutionInfo.list(properties.getVector("ExecStart"));
    }

    public List<ExecutionInfo> getExecStartPost() {
        return ExecutionInfo.list(properties.getVector("ExecStartPost"));
    }

    public List<ExecutionInfo> getExecStartPre() {
        return ExecutionInfo.list(properties.getVector("ExecStartPre"));
    }

    public List<ExecutionInfo> getExecStop() {
        return ExecutionInfo.list(properties.getVector("ExecStop"));
    }

    public List<ExecutionInfo> getExecStopPost() {
        return ExecutionInfo.list(properties.getVector("ExecStopPost"));
    }

    public String getFailureAction() {
        return properties.getString("FailureAction");
    }

    public long getFileDescriptorStoreMax() {
        return properties.getLong("FileDescriptorStoreMax");
    }

    public String getGroup() {
        return properties.getString("Group");
    }

    public boolean isGuessMainPID() {
        return properties.getBoolean("GuessMainPID");
    }

    public int getIOScheduling() {
        return properties.getInteger("IOScheduling");
    }

    public boolean isIgnoreSIGPIPE() {
        return properties.getBoolean("IgnoreSIGPIPE");
    }

    public Vector<String> getInaccessibleDirectories() {
        return properties.getVector("InaccessibleDirectories");
    }

    public String getKillMode() {
        return properties.getString("KillMode");
    }

    public int getKillSignal() {
        return properties.getInteger("KillSignal");
    }

    public BigInteger getLimitAS() {
        return properties.getBigInteger("LimitAS");
    }

    public BigInteger getLimitCORE() {
        return properties.getBigInteger("LimitCORE");
    }

    public BigInteger getLimitCPU() {
        return properties.getBigInteger("LimitCPU");
    }

    public BigInteger getLimitDATA() {
        return properties.getBigInteger("LimitDATA");
    }

    public BigInteger getLimitFSIZE() {
        return properties.getBigInteger("LimitFSIZE");
    }

    public BigInteger getLimitLOCKS() {
        return properties.getBigInteger("LimitLOCKS");
    }

    public BigInteger getLimitMEMLOCK() {
        return properties.getBigInteger("LimitMEMLOCK");
    }

    public BigInteger getLimitMSGQUEUE() {
        return properties.getBigInteger("LimitMSGQUEUE");
    }

    public BigInteger getLimitNICE() {
        return properties.getBigInteger("LimitNICE");
    }

    public BigInteger getLimitNOFILE() {
        return properties.getBigInteger("LimitNOFILE");
    }

    public BigInteger getLimitNPROC() {
        return properties.getBigInteger("LimitNPROC");
    }

    public BigInteger getLimitRSS() {
        return properties.getBigInteger("LimitRSS");
    }

    public BigInteger getLimitRTPRIO() {
        return properties.getBigInteger("LimitRTPRIO");
    }

    public BigInteger getLimitRTTIME() {
        return properties.getBigInteger("LimitRTTIME");
    }

    public BigInteger getLimitSIGPENDING() {
        return properties.getBigInteger("LimitSIGPENDING");
    }

    public BigInteger getLimitSTACK() {
        return properties.getBigInteger("LimitSTACK");
    }

    public int getMainPID() {
        return properties.getInteger("MainPID");
    }

    public boolean hasMemoryAccounting() {
        return properties.getBoolean("MemoryAccounting");
    }

    public BigInteger getMemoryCurrent() {
        return properties.getBigInteger("MemoryCurrent");
    }

    public BigInteger getMemoryLimit() {
        return properties.getBigInteger("MemoryLimit");
    }

    public BigInteger getMountFlags() {
        return properties.getBigInteger("MountFlags");
    }

    public long getNFileDescriptorStore() {
        return properties.getLong("NFileDescriptorStore");
    }

    public int getNice() {
        return properties.getInteger("Nice");
    }

    public boolean canNoNewPrivileges() {
        return properties.getBoolean("NoNewPrivileges");
    }

    public boolean isNonBlocking() {
        return properties.getBoolean("NonBlocking");
    }

    public String getNotifyAccess() {
        return properties.getString("NotifyAccess");
    }

    public int getOOMScoreAdjust() {
        return properties.getInteger("OOMScoreAdjust");
    }

    public String getPAMName() {
        return properties.getString("PAMName");
    }

    public String getPIDFile() {
        return properties.getString("PIDFile");
    }

    public Vector<String> getPassEnvironment() {
        return properties.getVector("PassEnvironment");
    }

    public boolean isPermissionsStartOnly() {
        return properties.getBoolean("PermissionsStartOnly");
    }

    public String getPersonality() {
        return properties.getString("Personality");
    }

    public boolean hasPrivateDevices() {
        return properties.getBoolean("PrivateDevices");
    }

    public boolean hasPrivateNetwork() {
        return properties.getBoolean("PrivateNetwork");
    }

    public boolean hasPrivateTmp() {
        return properties.getBoolean("PrivateTmp");
    }

    public String getProtectHome() {
        return properties.getString("ProtectHome");
    }

    public String getProtectSystem() {
        return properties.getString("ProtectSystem");
    }

    public Vector<String> getReadOnlyDirectories() {
        return properties.getVector("ReadOnlyDirectories");
    }

    public Vector<String> getReadWriteDirectories() {
        return properties.getVector("ReadWriteDirectories");
    }

    public String getRebootArgument() {
        return properties.getString("RebootArgument");
    }

    public boolean isRemainAfterExit() {
        return properties.getBoolean("RemainAfterExit");
    }

    public String getRestart() {
        return properties.getString("Restart");
    }

    public long getRestartUSec() {
        return properties.getLong("RestartUSec");
    }

    public String getResult() {
        return properties.getString("Result");
    }

    public String getRootDirectory() {
        return properties.getString("RootDirectory");
    }

    public boolean isRootDirectoryStartOnly() {
        return properties.getBoolean("RootDirectoryStartOnly");
    }

    public Vector<String> getRuntimeDirectory() {
        return properties.getVector("RuntimeDirectory");
    }

    public long getRuntimeDirectoryMode() {
        return properties.getLong("RuntimeDirectoryMode");
    }

    public SELinuxContext getSELinuxContext() {
        Object[] array = (Object[]) properties.getVariant("SELinuxContext").getValue();

        return new SELinuxContext(array);
    }

    public boolean isSameProcessGroup() {
        return properties.getBoolean("SameProcessGroup");
    }

    public int getSecureBits() {
        return properties.getInteger("SecureBits");
    }

    public boolean isSendSIGHUP() {
        return properties.getBoolean("SendSIGHUP");
    }

    public boolean isSendSIGKILL() {
        return properties.getBoolean("SendSIGKILL");
    }

    public String getSlice() {
        return properties.getString("Slice");
    }

    public String getStandardError() {
        return properties.getString("StandardError");
    }

    public String getStandardInput() {
        return properties.getString("StandardInput");
    }

    public String getStandardOutput() {
        return properties.getString("StandardOutput");
    }

    public String getStartLimitAction() {
        return properties.getString("StartLimitAction");
    }

    public long getStartLimitBurst() {
        return properties.getLong("StartLimitBurst");
    }

    public BigInteger getStartLimitInterval() {
        return properties.getBigInteger("StartLimitInterval");
    }

    public BigInteger getStartupBlockIOWeight() {
        return properties.getBigInteger("StartupBlockIOWeight");
    }

    public BigInteger getStartupCPUShares() {
        return properties.getBigInteger("StartupCPUShares");
    }

    public int getStatusErrno() {
        return properties.getInteger("StatusErrno");
    }

    public String getStatusText() {
        return properties.getString("StatusText");
    }

    public Vector<String> getSupplementaryGroups() {
        return properties.getVector("SupplementaryGroups");
    }

    public int getSyslogFacility() {
        return properties.getInteger("SyslogFacility");
    }

    public String getSyslogIdentifier() {
        return properties.getString("SyslogIdentifier");
    }

    public int getSyslogLevel() {
        return properties.getInteger("SyslogLevel");
    }

    public boolean hasSyslogLevelPrefix() {
        return properties.getBoolean("SyslogLevelPrefix");
    }

    public int getSyslogPriority() {
        return properties.getInteger("SyslogPriority");
    }

    public Vector<String> getSystemCallArchitectures() {
        return properties.getVector("SystemCallArchitectures");
    }

    public int getSystemCallErrorNumber() {
        return properties.getInteger("SystemCallErrorNumber");
    }

    public SystemCallFilter getSystemCallFilter() {
        Object[] array = (Object[]) properties.getVariant("SystemCallFilter").getValue();

        return new SystemCallFilter(array);
    }

    public String getTTYPath() {
        return properties.getString("TTYPath");
    }

    public boolean isTTYReset() {
        return properties.getBoolean("TTYReset");
    }

    public boolean isTTYVHangup() {
        return properties.getBoolean("TTYVHangup");
    }

    public boolean isTTYVTDisallocate() {
        return properties.getBoolean("TTYVTDisallocate");
    }

    public boolean hasTasksAccounting() {
        return properties.getBoolean("TasksAccounting");
    }

    public long getTasksCurrent() {
        return properties.getLong("TasksCurrent");
    }

    public long getTasksMax() {
        return properties.getLong("TasksMax");
    }

    public long getTimeoutStartUSec() {
        return properties.getLong("TimeoutStartUSec");
    }

    public long getTimeoutStopUSec() {
        return properties.getLong("TimeoutStopUSec");
    }

    public long getTimerSlackNSec() {
        return properties.getLong("TimerSlackNSec");
    }

    public String getType() {
        return properties.getString("Type");
    }

    public long getUMask() {
        return properties.getLong("UMask");
    }

    public String getUSBFunctionDescriptors() {
        return properties.getString("USBFunctionDescriptors");
    }

    public String getUSBFunctionStrings() {
        return properties.getString("USBFunctionStrings");
    }

    public String getUser() {
        return properties.getString("User");
    }

    public String getUtmpIdentifier() {
        return properties.getString("UtmpIdentifier");
    }

    public String getUtmpMode() {
        return properties.getString("UtmpMode");
    }

    public long getWatchdogTimestamp() {
        return properties.getLong("WatchdogTimestamp");
    }

    public long getWatchdogTimestampMonotonic() {
        return properties.getLong("WatchdogTimestampMonotonic");
    }

    public long getWatchdogUSec() {
        return properties.getLong("WatchdogUSec");
    }

    public String getWorkingDirectory() {
        return properties.getString("WorkingDirectory");
    }

    /**
     *
.AppArmorProfile                 property  (bs)           false ""                                 const
  .BlockIOAccounting               property  b              false                                    -
  .BlockIODeviceWeight             property  a(st)          0                                        -
  .BlockIOReadBandwidth            property  a(st)          0                                        -
  .BlockIOWeight                   property  t              18446744073709551615                     -
  .BlockIOWriteBandwidth           property  a(st)          0                                        -
  .BusName                         property  s              ""                                       const
  .CPUAccounting                   property  b              false                                    -
.CPUAffinity                     property  ay             0                                        const
  .CPUQuotaPerSecUSec              property  t              18446744073709551615                     -
  .CPUSchedulingPolicy             property  i              0                                        const
  .CPUSchedulingPriority           property  i              0                                        const
  .CPUSchedulingResetOnFork        property  b              false                                    const
  .CPUShares                       property  t              18446744073709551615                     -
  .CPUUsageNSec                    property  t              18446744073709551615                     -
  .Capabilities                    property  s              ""                                       const
  .CapabilityBoundingSet           property  t              18446744073709551615                     const
  .ControlGroup                    property  s              "/system.slice/cronie.service"           -
  .ControlPID                      property  u              0                                        emits-change
  .Delegate                        property  b              false                                    -
.DeviceAllow                     property  a(ss)          0                                        -
  .DevicePolicy                    property  s              "auto"                                   -
  .Environment                     property  as             0                                        const
  .EnvironmentFiles                property  a(sb)          0                                        const
  .ExecMainCode                    property  i              0                                        emits-change
  .ExecMainExitTimestamp           property  t              0                                        emits-change
  .ExecMainExitTimestampMonotonic  property  t              0                                        emits-change
  .ExecMainPID                     property  u              369                                      emits-change
  .ExecMainStartTimestamp          property  t              1454158017072144                         emits-change
  .ExecMainStartTimestampMonotonic property  t              7581134                                  emits-change
  .ExecMainStatus                  property  i              0                                        emits-change
  .ExecReload                      property  a(sasbttttuii) 1 "/usr/bin/kill" 3 "/usr/bin/kill" "... emits-invalidation
  .ExecStart                       property  a(sasbttttuii) 1 "/usr/bin/crond" 2 "/usr/bin/crond"... emits-invalidation
  .ExecStartPost                   property  a(sasbttttuii) 0                                        emits-invalidation
  .ExecStartPre                    property  a(sasbttttuii) 0                                        emits-invalidation
  .ExecStop                        property  a(sasbttttuii) 0                                        emits-invalidation
  .ExecStopPost                    property  a(sasbttttuii) 0                                        emits-invalidation
  .FailureAction                   property  s              "none"                                   const
  .FileDescriptorStoreMax          property  u              0                                        const
  .Group                           property  s              ""                                       const
  .GuessMainPID                    property  b              true                                     const
  .IOScheduling                    property  i              0                                        const
  .IgnoreSIGPIPE                   property  b              true                                     const
  .InaccessibleDirectories         property  as             0                                        const
  .KillMode                        property  s              "process"                                const
  .KillSignal                      property  i              15                                       const
  .LimitAS                         property  t              18446744073709551615                     const
  .LimitCORE                       property  t              18446744073709551615                     const
  .LimitCPU                        property  t              18446744073709551615                     const
  .LimitDATA                       property  t              18446744073709551615                     const
  .LimitFSIZE                      property  t              18446744073709551615                     const
  .LimitLOCKS                      property  t              18446744073709551615                     const
  .LimitMEMLOCK                    property  t              65536                                    const
  .LimitMSGQUEUE                   property  t              819200                                   const
  .LimitNICE                       property  t              0                                        const
  .LimitNOFILE                     property  t              4096                                     const
  .LimitNPROC                      property  t              48013                                    const
  .LimitRSS                        property  t              18446744073709551615                     const
  .LimitRTPRIO                     property  t              0                                        const
  .LimitRTTIME                     property  t              18446744073709551615                     const
  .LimitSIGPENDING                 property  t              48013                                    const
  .LimitSTACK                      property  t              18446744073709551615                     const
  .MainPID                         property  u              369                                      emits-change
  .MemoryAccounting                property  b              false                                    -
  .MemoryCurrent                   property  t              18446744073709551615                     -
  .MemoryLimit                     property  t              18446744073709551615                     -
  .MountFlags                      property  t              0                                        const
  .NFileDescriptorStore            property  u              0                                        -
  .Nice                            property  i              0                                        const
  .NoNewPrivileges                 property  b              false                                    const
  .NonBlocking                     property  b              false                                    const
  .NotifyAccess                    property  s              "none"                                   const
  .OOMScoreAdjust                  property  i              0                                        const
  .PAMName                         property  s              ""                                       const
  .PIDFile                         property  s              ""                                       const
  .PassEnvironment                 property  as             0                                        const
  .PermissionsStartOnly            property  b              false                                    const
  .Personality                     property  s              ""                                       const
  .PrivateDevices                  property  b              false                                    const
  .PrivateNetwork                  property  b              false                                    const
  .PrivateTmp                      property  b              false                                    const
  .ProtectHome                     property  s              "no"                                     const
  .ProtectSystem                   property  s              "no"                                     const
  .ReadOnlyDirectories             property  as             0                                        const
  .ReadWriteDirectories            property  as             0                                        const
  .RebootArgument                  property  s              ""                                       const
  .RemainAfterExit                 property  b              false                                    const
  .Restart                         property  s              "always"                                 const
  .RestartUSec                     property  t              100000                                   const
.RestrictAddressFamilies         property  (bas)          false 0                                  const
  .Result                          property  s              "success"                                emits-change
  .RootDirectory                   property  s              ""                                       const
  .RootDirectoryStartOnly          property  b              false                                    const
  .RuntimeDirectory                property  as             0                                        const
  .RuntimeDirectoryMode            property  u              493                                      const
  .SELinuxContext                  property  (bs)           false ""                                 const
  .SameProcessGroup                property  b              false                                    const
  .SecureBits                      property  i              0                                        const
  .SendSIGHUP                      property  b              false                                    const
  .SendSIGKILL                     property  b              true                                     const
  .Slice                           property  s              "system.slice"                           -
.SmackProcessLabel               property  (bs)           false ""                                 const
  .StandardError                   property  s              "inherit"                                const
  .StandardInput                   property  s              "null"                                   const
  .StandardOutput                  property  s              "journal"                                const
  .StartLimitAction                property  s              "none"                                   const
  .StartLimitBurst                 property  u              5                                        const
  .StartLimitInterval              property  t              10000000                                 const
  .StartupBlockIOWeight            property  t              18446744073709551615                     -
  .StartupCPUShares                property  t              18446744073709551615                     -
  .StatusErrno                     property  i              0                                        emits-change
  .StatusText                      property  s              ""                                       emits-change
  .SupplementaryGroups             property  as             0                                        const
  .SyslogFacility                  property  i              3                                        const
  .SyslogIdentifier                property  s              ""                                       const
  .SyslogLevel                     property  i              6                                        const
  .SyslogLevelPrefix               property  b              true                                     const
  .SyslogPriority                  property  i              30                                       const
  .SystemCallArchitectures         property  as             0                                        const
  .SystemCallErrorNumber           property  i              0                                        const
  .SystemCallFilter                property  (bas)          false 0                                  const
  .TTYPath                         property  s              ""                                       const
  .TTYReset                        property  b              false                                    const
  .TTYVHangup                      property  b              false                                    const
  .TTYVTDisallocate                property  b              false                                    const
  .TasksAccounting                 property  b              true                                     -
  .TasksCurrent                    property  t              1                                        -
  .TasksMax                        property  t              512                                      -
  .TimeoutStartUSec                property  t              90000000                                 const
  .TimeoutStopUSec                 property  t              90000000                                 const
  .TimerSlackNSec                  property  t              50000                                    const
  .Type                            property  s              "simple"                                 const
  .UMask                           property  u              18                                       const
  .USBFunctionDescriptors          property  s              ""                                       emits-change
  .USBFunctionStrings              property  s              ""                                       emits-change
  .User                            property  s              ""                                       const
  .UtmpIdentifier                  property  s              ""                                       const
  .UtmpMode                        property  s              "init"                                   const
  .WatchdogTimestamp               property  t              1454158017072175                         -
  .WatchdogTimestampMonotonic      property  t              7581165                                  -
  .WatchdogUSec                    property  t              0                                        const
  .WorkingDirectory                property  s              ""                                       const
     */

}