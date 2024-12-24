package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= 28) {
            return info.getLongVersionCode();
        }
        return (long) info.versionCode;
    }

    public static List<Signature> getSignatures(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        Signature[] array;
        if (Build.VERSION.SDK_INT >= 28) {
            SigningInfo signingInfo = packageManager.getPackageInfo(packageName, 134217728).signingInfo;
            if (Api28Impl.hasMultipleSigners(signingInfo)) {
                array = Api28Impl.getApkContentsSigners(signingInfo);
            } else {
                array = Api28Impl.getSigningCertificateHistory(signingInfo);
            }
        } else {
            array = packageManager.getPackageInfo(packageName, 64).signatures;
        }
        if (array == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(array);
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hasSignatures(android.content.pm.PackageManager r10, java.lang.String r11, java.util.Map<byte[], java.lang.Integer> r12, boolean r13) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            boolean r0 = r12.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.Set r0 = r12.keySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0010:
            boolean r3 = r2.hasNext()
            java.lang.String r4 = "Unsupported certificate type "
            if (r3 == 0) goto L_0x007c
            java.lang.Object r3 = r2.next()
            byte[] r3 = (byte[]) r3
            if (r3 == 0) goto L_0x0065
            java.lang.Object r5 = r12.get(r3)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L_0x004e
            int r6 = r5.intValue()
            switch(r6) {
                case 0: goto L_0x004c;
                case 1: goto L_0x004c;
                default: goto L_0x002f;
            }
        L_0x002f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r5)
            java.lang.String r4 = " when verifying "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x004c:
            goto L_0x0010
        L_0x004e:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Type must be specified for cert when verifying "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0065:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Cert byte array cannot be null when verifying "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x007c:
            java.util.List r2 = getSignatures(r10, r11)
            r3 = 1
            if (r13 != 0) goto L_0x00ac
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 28
            if (r5 < r6) goto L_0x00ac
            java.util.Iterator r4 = r0.iterator()
        L_0x008d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00ab
            java.lang.Object r5 = r4.next()
            byte[] r5 = (byte[]) r5
            java.lang.Object r6 = r12.get(r5)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r7 = r6.intValue()
            boolean r7 = androidx.core.content.pm.PackageInfoCompat.Api28Impl.hasSigningCertificate(r10, r11, r5, r7)
            if (r7 != 0) goto L_0x00aa
            return r1
        L_0x00aa:
            goto L_0x008d
        L_0x00ab:
            return r3
        L_0x00ac:
            int r5 = r2.size()
            if (r5 == 0) goto L_0x013b
            int r5 = r12.size()
            int r6 = r2.size()
            if (r5 > r6) goto L_0x013b
            if (r13 == 0) goto L_0x00c9
            int r5 = r12.size()
            int r6 = r2.size()
            if (r5 == r6) goto L_0x00c9
            goto L_0x013b
        L_0x00c9:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            boolean r5 = r12.containsValue(r5)
            r6 = 0
            if (r5 == 0) goto L_0x00f4
            int r7 = r2.size()
            byte[][] r6 = new byte[r7][]
            r7 = 0
        L_0x00db:
            int r8 = r2.size()
            if (r7 >= r8) goto L_0x00f4
            java.lang.Object r8 = r2.get(r7)
            android.content.pm.Signature r8 = (android.content.pm.Signature) r8
            byte[] r8 = r8.toByteArray()
            byte[] r8 = computeSHA256Digest(r8)
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x00db
        L_0x00f4:
            java.util.Iterator r7 = r0.iterator()
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x013a
            java.lang.Object r7 = r7.next()
            byte[] r7 = (byte[]) r7
            java.lang.Object r8 = r12.get(r7)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r9 = r8.intValue()
            switch(r9) {
                case 0: goto L_0x012d;
                case 1: goto L_0x0126;
                default: goto L_0x0111;
            }
        L_0x0111:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r1.<init>(r3)
            throw r1
        L_0x0126:
            boolean r4 = byteArrayContains(r6, r7)
            if (r4 != 0) goto L_0x0139
            return r1
        L_0x012d:
            android.content.pm.Signature r4 = new android.content.pm.Signature
            r4.<init>(r7)
            boolean r9 = r2.contains(r4)
            if (r9 != 0) goto L_0x0139
            return r1
        L_0x0139:
            return r3
        L_0x013a:
            return r1
        L_0x013b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.PackageInfoCompat.hasSignatures(android.content.pm.PackageManager, java.lang.String, java.util.Map, boolean):boolean");
    }

    private static boolean byteArrayContains(byte[][] array, byte[] expected) {
        for (byte[] item : array) {
            if (Arrays.equals(expected, item)) {
                return true;
            }
        }
        return false;
    }

    private static byte[] computeSHA256Digest(byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA256").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Device doesn't support SHA256 cert checking", e);
        }
    }

    private PackageInfoCompat() {
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        static boolean hasSigningCertificate(PackageManager packageManager, String packageName, byte[] bytes, int type) {
            return packageManager.hasSigningCertificate(packageName, bytes, type);
        }

        static boolean hasMultipleSigners(SigningInfo signingInfo) {
            return signingInfo.hasMultipleSigners();
        }

        static Signature[] getApkContentsSigners(SigningInfo signingInfo) {
            return signingInfo.getApkContentsSigners();
        }

        static Signature[] getSigningCertificateHistory(SigningInfo signingInfo) {
            return signingInfo.getSigningCertificateHistory();
        }
    }
}
