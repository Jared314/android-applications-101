/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/jared314/Projects/android-applications-101/android-example3/src/com/bluecasedevelopment/example3/IRepairReportService.aidl
 */
package com.bluecasedevelopment.example3;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IRepairReportService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.bluecasedevelopment.example3.IRepairReportService
{
private static final java.lang.String DESCRIPTOR = "com.bluecasedevelopment.example3.IRepairReportService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IRepairReportService interface,
 * generating a proxy if needed.
 */
public static com.bluecasedevelopment.example3.IRepairReportService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.bluecasedevelopment.example3.IRepairReportService))) {
return ((com.bluecasedevelopment.example3.IRepairReportService)iin);
}
return new com.bluecasedevelopment.example3.IRepairReportService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_insert:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.insert(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_update:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.update(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_delete:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.delete(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.bluecasedevelopment.example3.IRepairReportService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void insert(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_insert, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void update(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_update, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void delete(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_delete, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_insert = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_update = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_delete = (IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void insert(long id) throws android.os.RemoteException;
public void update(long id) throws android.os.RemoteException;
public void delete(long id) throws android.os.RemoteException;
}
