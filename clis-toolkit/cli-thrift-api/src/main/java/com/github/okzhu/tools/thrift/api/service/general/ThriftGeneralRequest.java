/**
 * Autogenerated by Thrift Compiler (0.13.0)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package com.github.okzhu.tools.thrift.api.service.general;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * 如果仅仅是想优化网络层面的 可以直接使用这个类来处理
 * 他的处理过程是 如果你以前的是一个http 请求。
 * 那么他就是直接使用内部的类调用 srping Servlet 可以直接内部模拟一个http 请求。
 * 不用额外处理。返回的结果和原来是一样的
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-06-16")
public class ThriftGeneralRequest implements org.apache.thrift.TBase<ThriftGeneralRequest, ThriftGeneralRequest._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftGeneralRequest> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftGeneralRequest");

    private static final org.apache.thrift.protocol.TField HEADERS_FIELD_DESC = new org.apache.thrift.protocol.TField("headers", org.apache.thrift.protocol.TType.MAP, (short) 1);
    private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short) 2);
    private static final org.apache.thrift.protocol.TField METHOD_FIELD_DESC = new org.apache.thrift.protocol.TField("method", org.apache.thrift.protocol.TType.STRING, (short) 3);
    private static final org.apache.thrift.protocol.TField BODY_FIELD_DESC = new org.apache.thrift.protocol.TField("body", org.apache.thrift.protocol.TType.STRING, (short) 4);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ThriftGeneralRequestStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ThriftGeneralRequestTupleSchemeFactory();

    /**
     * 就是HTTP的请求头。你 http 是什么这个就是什么
     */
    public @org.apache.thrift.annotation.Nullable
    java.util.Map<java.lang.String, java.lang.String> headers; // required
    /**
     * 就是HTTP的 Url。你 http 是什么这个就是什么
     */
    public @org.apache.thrift.annotation.Nullable
    java.lang.String url; // required
    /**
     * 就是HTTP的 method。你 http 是什么这个就是什么
     */
    public @org.apache.thrift.annotation.Nullable
    java.lang.String method; // required
    /**
     * 就是HTTP的 body。你 http 是什么这个就是什么
     */
    public @org.apache.thrift.annotation.Nullable
    java.lang.String body; // optional

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        /**
         * 就是HTTP的请求头。你 http 是什么这个就是什么
         */
        HEADERS((short) 1, "headers"),
        /**
         * 就是HTTP的 Url。你 http 是什么这个就是什么
         */
        URL((short) 2, "url"),
        /**
         * 就是HTTP的 method。你 http 是什么这个就是什么
         */
        METHOD((short) 3, "method"),
        /**
         * 就是HTTP的 body。你 http 是什么这个就是什么
         */
        BODY((short) 4, "body");

        private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

        static {
            for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // HEADERS
                    return HEADERS;
                case 2: // URL
                    return URL;
                case 3: // METHOD
                    return METHOD;
                case 4: // BODY
                    return BODY;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByName(java.lang.String name) {
            return byName.get(name);
        }

        private final short _thriftId;
        private final java.lang.String _fieldName;

        _Fields(short thriftId, java.lang.String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public java.lang.String getFieldName() {
            return _fieldName;
        }
    }

    // isset id assignments
    private static final _Fields optionals[] = {_Fields.BODY};
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.HEADERS, new org.apache.thrift.meta_data.FieldMetaData("headers", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP,
                        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING),
                        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
        tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.METHOD, new org.apache.thrift.meta_data.FieldMetaData("method", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.BODY, new org.apache.thrift.meta_data.FieldMetaData("body", org.apache.thrift.TFieldRequirementType.OPTIONAL,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftGeneralRequest.class, metaDataMap);
    }

    public ThriftGeneralRequest() {
    }

    public ThriftGeneralRequest(
            java.util.Map<java.lang.String, java.lang.String> headers,
            java.lang.String url,
            java.lang.String method) {
        this();
        this.headers = headers;
        this.url = url;
        this.method = method;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public ThriftGeneralRequest(ThriftGeneralRequest other) {
        if (other.isSetHeaders()) {
            java.util.Map<java.lang.String, java.lang.String> __this__headers = new java.util.HashMap<java.lang.String, java.lang.String>(other.headers);
            this.headers = __this__headers;
        }
        if (other.isSetUrl()) {
            this.url = other.url;
        }
        if (other.isSetMethod()) {
            this.method = other.method;
        }
        if (other.isSetBody()) {
            this.body = other.body;
        }
    }

    public ThriftGeneralRequest deepCopy() {
        return new ThriftGeneralRequest(this);
    }

    @Override
    public void clear() {
        this.headers = null;
        this.url = null;
        this.method = null;
        this.body = null;
    }

    public int getHeadersSize() {
        return (this.headers == null) ? 0 : this.headers.size();
    }

    public void putToHeaders(java.lang.String key, java.lang.String val) {
        if (this.headers == null) {
            this.headers = new java.util.HashMap<java.lang.String, java.lang.String>();
        }
        this.headers.put(key, val);
    }

    /**
     * 就是HTTP的请求头。你 http 是什么这个就是什么
     */
    @org.apache.thrift.annotation.Nullable
    public java.util.Map<java.lang.String, java.lang.String> getHeaders() {
        return this.headers;
    }

    /**
     * 就是HTTP的请求头。你 http 是什么这个就是什么
     */
    public ThriftGeneralRequest setHeaders(@org.apache.thrift.annotation.Nullable java.util.Map<java.lang.String, java.lang.String> headers) {
        this.headers = headers;
        return this;
    }

    public void unsetHeaders() {
        this.headers = null;
    }

    /** Returns true if field headers is set (has been assigned a value) and false otherwise */
    public boolean isSetHeaders() {
        return this.headers != null;
    }

    public void setHeadersIsSet(boolean value) {
        if (!value) {
            this.headers = null;
        }
    }

    /**
     * 就是HTTP的 Url。你 http 是什么这个就是什么
     */
    @org.apache.thrift.annotation.Nullable
    public java.lang.String getUrl() {
        return this.url;
    }

    /**
     * 就是HTTP的 Url。你 http 是什么这个就是什么
     */
    public ThriftGeneralRequest setUrl(@org.apache.thrift.annotation.Nullable java.lang.String url) {
        this.url = url;
        return this;
    }

    public void unsetUrl() {
        this.url = null;
    }

    /** Returns true if field url is set (has been assigned a value) and false otherwise */
    public boolean isSetUrl() {
        return this.url != null;
    }

    public void setUrlIsSet(boolean value) {
        if (!value) {
            this.url = null;
        }
    }

    /**
     * 就是HTTP的 method。你 http 是什么这个就是什么
     */
    @org.apache.thrift.annotation.Nullable
    public java.lang.String getMethod() {
        return this.method;
    }

    /**
     * 就是HTTP的 method。你 http 是什么这个就是什么
     */
    public ThriftGeneralRequest setMethod(@org.apache.thrift.annotation.Nullable java.lang.String method) {
        this.method = method;
        return this;
    }

    public void unsetMethod() {
        this.method = null;
    }

    /** Returns true if field method is set (has been assigned a value) and false otherwise */
    public boolean isSetMethod() {
        return this.method != null;
    }

    public void setMethodIsSet(boolean value) {
        if (!value) {
            this.method = null;
        }
    }

    /**
     * 就是HTTP的 body。你 http 是什么这个就是什么
     */
    @org.apache.thrift.annotation.Nullable
    public java.lang.String getBody() {
        return this.body;
    }

    /**
     * 就是HTTP的 body。你 http 是什么这个就是什么
     */
    public ThriftGeneralRequest setBody(@org.apache.thrift.annotation.Nullable java.lang.String body) {
        this.body = body;
        return this;
    }

    public void unsetBody() {
        this.body = null;
    }

    /** Returns true if field body is set (has been assigned a value) and false otherwise */
    public boolean isSetBody() {
        return this.body != null;
    }

    public void setBodyIsSet(boolean value) {
        if (!value) {
            this.body = null;
        }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
        switch (field) {
            case HEADERS:
                if (value == null) {
                    unsetHeaders();
                } else {
                    setHeaders((java.util.Map<java.lang.String, java.lang.String>) value);
                }
                break;

            case URL:
                if (value == null) {
                    unsetUrl();
                } else {
                    setUrl((java.lang.String) value);
                }
                break;

            case METHOD:
                if (value == null) {
                    unsetMethod();
                } else {
                    setMethod((java.lang.String) value);
                }
                break;

            case BODY:
                if (value == null) {
                    unsetBody();
                } else {
                    setBody((java.lang.String) value);
                }
                break;

        }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
        switch (field) {
            case HEADERS:
                return getHeaders();

            case URL:
                return getUrl();

            case METHOD:
                return getMethod();

            case BODY:
                return getBody();

        }
        throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new java.lang.IllegalArgumentException();
        }

        switch (field) {
            case HEADERS:
                return isSetHeaders();
            case URL:
                return isSetUrl();
            case METHOD:
                return isSetMethod();
            case BODY:
                return isSetBody();
        }
        throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
        if (that == null)
            return false;
        if (that instanceof ThriftGeneralRequest)
            return this.equals((ThriftGeneralRequest) that);
        return false;
    }

    public boolean equals(ThriftGeneralRequest that) {
        if (that == null)
            return false;
        if (this == that)
            return true;

        boolean this_present_headers = true && this.isSetHeaders();
        boolean that_present_headers = true && that.isSetHeaders();
        if (this_present_headers || that_present_headers) {
            if (!(this_present_headers && that_present_headers))
                return false;
            if (!this.headers.equals(that.headers))
                return false;
        }

        boolean this_present_url = true && this.isSetUrl();
        boolean that_present_url = true && that.isSetUrl();
        if (this_present_url || that_present_url) {
            if (!(this_present_url && that_present_url))
                return false;
            if (!this.url.equals(that.url))
                return false;
        }

        boolean this_present_method = true && this.isSetMethod();
        boolean that_present_method = true && that.isSetMethod();
        if (this_present_method || that_present_method) {
            if (!(this_present_method && that_present_method))
                return false;
            if (!this.method.equals(that.method))
                return false;
        }

        boolean this_present_body = true && this.isSetBody();
        boolean that_present_body = true && that.isSetBody();
        if (this_present_body || that_present_body) {
            if (!(this_present_body && that_present_body))
                return false;
            if (!this.body.equals(that.body))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 8191 + ((isSetHeaders()) ? 131071 : 524287);
        if (isSetHeaders())
            hashCode = hashCode * 8191 + headers.hashCode();

        hashCode = hashCode * 8191 + ((isSetUrl()) ? 131071 : 524287);
        if (isSetUrl())
            hashCode = hashCode * 8191 + url.hashCode();

        hashCode = hashCode * 8191 + ((isSetMethod()) ? 131071 : 524287);
        if (isSetMethod())
            hashCode = hashCode * 8191 + method.hashCode();

        hashCode = hashCode * 8191 + ((isSetBody()) ? 131071 : 524287);
        if (isSetBody())
            hashCode = hashCode * 8191 + body.hashCode();

        return hashCode;
    }

    @Override
    public int compareTo(ThriftGeneralRequest other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = java.lang.Boolean.valueOf(isSetHeaders()).compareTo(other.isSetHeaders());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHeaders()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.headers, other.headers);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = java.lang.Boolean.valueOf(isSetUrl()).compareTo(other.isSetUrl());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetUrl()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, other.url);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = java.lang.Boolean.valueOf(isSetMethod()).compareTo(other.isSetMethod());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMethod()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.method, other.method);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = java.lang.Boolean.valueOf(isSetBody()).compareTo(other.isSetBody());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetBody()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.body, other.body);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
        scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
        scheme(oprot).write(oprot, this);
    }

    @Override
    public java.lang.String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("ThriftGeneralRequest(");
        boolean first = true;

        sb.append("headers:");
        if (this.headers == null) {
            sb.append("null");
        } else {
            sb.append(this.headers);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("url:");
        if (this.url == null) {
            sb.append("null");
        } else {
            sb.append(this.url);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("method:");
        if (this.method == null) {
            sb.append("null");
        } else {
            sb.append(this.method);
        }
        first = false;
        if (isSetBody()) {
            if (!first) sb.append(", ");
            sb.append("body:");
            if (this.body == null) {
                sb.append("null");
            } else {
                sb.append(this.body);
            }
            first = false;
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
        try {
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private static class ThriftGeneralRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public ThriftGeneralRequestStandardScheme getScheme() {
            return new ThriftGeneralRequestStandardScheme();
        }
    }

    private static class ThriftGeneralRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<ThriftGeneralRequest> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftGeneralRequest struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // HEADERS
                        if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
                            {
                                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                                struct.headers = new java.util.HashMap<java.lang.String, java.lang.String>(2 * _map0.size);
                                @org.apache.thrift.annotation.Nullable java.lang.String _key1;
                                @org.apache.thrift.annotation.Nullable java.lang.String _val2;
                                for (int _i3 = 0; _i3 < _map0.size; ++_i3) {
                                    _key1 = iprot.readString();
                                    _val2 = iprot.readString();
                                    struct.headers.put(_key1, _val2);
                                }
                                iprot.readMapEnd();
                            }
                            struct.setHeadersIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // URL
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.url = iprot.readString();
                            struct.setUrlIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // METHOD
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.method = iprot.readString();
                            struct.setMethodIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 4: // BODY
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.body = iprot.readString();
                            struct.setBodyIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftGeneralRequest struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.headers != null) {
                oprot.writeFieldBegin(HEADERS_FIELD_DESC);
                {
                    oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.headers.size()));
                    for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter4 : struct.headers.entrySet()) {
                        oprot.writeString(_iter4.getKey());
                        oprot.writeString(_iter4.getValue());
                    }
                    oprot.writeMapEnd();
                }
                oprot.writeFieldEnd();
            }
            if (struct.url != null) {
                oprot.writeFieldBegin(URL_FIELD_DESC);
                oprot.writeString(struct.url);
                oprot.writeFieldEnd();
            }
            if (struct.method != null) {
                oprot.writeFieldBegin(METHOD_FIELD_DESC);
                oprot.writeString(struct.method);
                oprot.writeFieldEnd();
            }
            if (struct.body != null) {
                if (struct.isSetBody()) {
                    oprot.writeFieldBegin(BODY_FIELD_DESC);
                    oprot.writeString(struct.body);
                    oprot.writeFieldEnd();
                }
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class ThriftGeneralRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public ThriftGeneralRequestTupleScheme getScheme() {
            return new ThriftGeneralRequestTupleScheme();
        }
    }

    private static class ThriftGeneralRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<ThriftGeneralRequest> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, ThriftGeneralRequest struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            java.util.BitSet optionals = new java.util.BitSet();
            if (struct.isSetHeaders()) {
                optionals.set(0);
            }
            if (struct.isSetUrl()) {
                optionals.set(1);
            }
            if (struct.isSetMethod()) {
                optionals.set(2);
            }
            if (struct.isSetBody()) {
                optionals.set(3);
            }
            oprot.writeBitSet(optionals, 4);
            if (struct.isSetHeaders()) {
                {
                    oprot.writeI32(struct.headers.size());
                    for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter5 : struct.headers.entrySet()) {
                        oprot.writeString(_iter5.getKey());
                        oprot.writeString(_iter5.getValue());
                    }
                }
            }
            if (struct.isSetUrl()) {
                oprot.writeString(struct.url);
            }
            if (struct.isSetMethod()) {
                oprot.writeString(struct.method);
            }
            if (struct.isSetBody()) {
                oprot.writeString(struct.body);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, ThriftGeneralRequest struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            java.util.BitSet incoming = iprot.readBitSet(4);
            if (incoming.get(0)) {
                {
                    org.apache.thrift.protocol.TMap _map6 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
                    struct.headers = new java.util.HashMap<java.lang.String, java.lang.String>(2 * _map6.size);
                    @org.apache.thrift.annotation.Nullable java.lang.String _key7;
                    @org.apache.thrift.annotation.Nullable java.lang.String _val8;
                    for (int _i9 = 0; _i9 < _map6.size; ++_i9) {
                        _key7 = iprot.readString();
                        _val8 = iprot.readString();
                        struct.headers.put(_key7, _val8);
                    }
                }
                struct.setHeadersIsSet(true);
            }
            if (incoming.get(1)) {
                struct.url = iprot.readString();
                struct.setUrlIsSet(true);
            }
            if (incoming.get(2)) {
                struct.method = iprot.readString();
                struct.setMethodIsSet(true);
            }
            if (incoming.get(3)) {
                struct.body = iprot.readString();
                struct.setBodyIsSet(true);
            }
        }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
        return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
}

