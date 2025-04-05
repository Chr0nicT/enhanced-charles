package com.notcat.patching.transformers.impl;

import com.notcat.patching.ClassInjector;
import com.notcat.patching.TransformedClass;
import com.notcat.patching.transformers.ITransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;

import java.util.ArrayList;
import java.util.Arrays;

public class JA3Transformer implements ITransformer {

    //region Members Names
    public final String OVERVIEW_CLASS_PATH = "com.xk72.charles.gui.transaction.general.h";
    public final String INIT_METHOD_NAME = "b";
    public final String LOAD_METHOD_NAME = "TKU";
    //endregion

    @Override
    public Iterable<TransformedClass> transform(ClassPool classPool) {

        ArrayList<TransformedClass> transformedClasses = new ArrayList<>();

        try {

            ClassInjector.inject(Arrays.asList(
                    "com.xk72.enhancedcharles.JA3Utils",
                    "com.xk72.enhancedcharles.JA3Utils$1",
                    "com.xk72.enhancedcharles.JA3Utils$2",
                    "com.xk72.enhancedcharles.JA3Utils$3"
            ), classPool, transformedClasses);

            //region Patch Overview Tab

            classPool.importPackage("com.xk72.charles.gui.lib.treetable");
            classPool.importPackage("com.xk72.charles.model");
            classPool.importPackage("javax.swing.tree.MutableTreeNode");

            CtClass overviewClass = classPool.get(OVERVIEW_CLASS_PATH);

            overviewClass.addField(CtField.make("private DefaultTreeTableNode ja3Node;", overviewClass));
            overviewClass.addField(CtField.make("private DefaultTreeTableNode ja3PlainNode;", overviewClass));
            overviewClass.addField(CtField.make("private DefaultTreeTableNode ja3HashNode;", overviewClass));
            overviewClass.addField(CtField.make("private DefaultTreeTableNode ja3IdNode;", overviewClass));
            //overviewClass.addField(CtField.make("private DefaultTreeTableNode rawClientHelloNode;", overviewClass));

            //region Patch Init
            overviewClass.getDeclaredMethod(INIT_METHOD_NAME).
                    setBody("{this.a.add(this.d = new DefaultTreeTableNode(\"URL\"));\n" +
                            "      this.a.add(this.f = new DefaultTreeTableNode(\"Status\"));\n" +
                            "      this.a.add(this.g = new DefaultTreeTableNode(\"Failure\"));\n" +
                            "      this.a.add(this.e = new DefaultTreeTableNode(\"Notes\"));\n" +
                            "      this.a.add(this.h = new DefaultTreeTableNode(\"Response Code\"));\n" +
                            "      this.a.add(this.i = new DefaultTreeTableNode(\"Protocol\"));\n" +
                            "      this.a.add(this.j = new DefaultTreeTableNode(\"TLS\"));\n" +
                            "      this.j.add(this.ja3Node = new DefaultTreeTableNode(\"JA3\"));\n" +
                            "      this.ja3Node.add(this.ja3PlainNode = new DefaultTreeTableNode(\"Plain\"));\n" +
                            "      this.ja3Node.add(this.ja3HashNode = new DefaultTreeTableNode(\"Hash\"));\n" +
                            "      this.ja3Node.add(this.ja3IdNode = new DefaultTreeTableNode(\"Identified As\"));\n" +
                            "      this.j.add(this.p = new DefaultTreeTableNode(\"Protocol\"));\n" +
                            "      this.p.add(this.r = new DefaultTreeTableNode(\"Client Supported\"));\n" +
                            "      this.p.add(this.s = new DefaultTreeTableNode(\"Charles to Client\"));\n" +
                            "      this.p.add(this.q = new DefaultTreeTableNode(\"Server Chosen\"));\n" +
                            "      this.j.add(this.I = new DefaultTreeTableNode(\"Alert Code\"));\n" +
                            "      this.j.add(this.k = new DefaultTreeTableNode(\"Session Resumed\"));\n" +
                            "      this.k.add(this.l = new DefaultTreeTableNode(\"Client Requested\"));\n" +
                            "      this.k.add(this.n = new DefaultTreeTableNode(\"Client Session ID\"));\n" +
                            "      this.k.add(this.o = new DefaultTreeTableNode(\"Charles Requested\"));\n" +
                            "      this.k.add(this.m = new DefaultTreeTableNode(\"Server Session ID\"));\n" +
                            "      this.j.add((MutableTreeNode)(this.t = new DefaultTreeTableNode(\"Cipher Suite\")));\n" +
                            "      this.t.add((MutableTreeNode)(this.u = new DefaultTreeTableNode(\"Client Supported\")));\n" +
                            "      this.t.add((MutableTreeNode)(this.w = new DefaultTreeTableNode(\"Charles to Client\")));\n" +
                            "      this.t.add((MutableTreeNode)(this.v = new DefaultTreeTableNode(\"Server Chosen\")));\n" +
                            "      this.j.add((MutableTreeNode)(this.x = new DefaultTreeTableNode(\"ALPN\")));\n" +
                            "      this.x.add((MutableTreeNode)(this.y = new DefaultTreeTableNode(\"Client Supported\")));\n" +
                            "      this.x.add((MutableTreeNode)(this.z = new DefaultTreeTableNode(\"Charles Supported\")));\n" +
                            "      this.x.add((MutableTreeNode)(this.A = new DefaultTreeTableNode(\"Server Chosen\")));\n" +
                            "      this.j.add((MutableTreeNode)(this.B = new DefaultTreeTableNode(\"Client Certificates\")));\n" +
                            "      this.j.add((MutableTreeNode)(this.C = new DefaultTreeTableNode(\"Server Certificates\")));\n" +
                            "      this.j.add((MutableTreeNode)(this.D = new DefaultTreeTableNode(\"Extensions\")));\n" +
                            "      this.D.add((MutableTreeNode)(this.E = new DefaultTreeTableNode(\"Client\")));\n" +
                            "      this.D.add((MutableTreeNode)(this.F = new DefaultTreeTableNode(\"Charles to Client\")));\n" +
                            "      this.D.add((MutableTreeNode)(this.G = new DefaultTreeTableNode(\"Charles to Server\")));\n" +
                            "      this.D.add((MutableTreeNode)(this.H = new DefaultTreeTableNode(\"Server\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.J = new DefaultTreeTableNode(\"Method\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.ar = new DefaultTreeTableNode(\"Kept Alive\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.K = new DefaultTreeTableNode(\"Content-Type\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.L = new DefaultTreeTableNode(\"Client Address\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.M = new DefaultTreeTableNode(\"Remote Address\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.at = new DefaultTreeTableNode(\"Tags\")));\n" +
                            "      this.a.add((MutableTreeNode)(this.as = new DefaultTreeTableNode(\"Connection\")));\n" +
                            "      this.au = new DefaultTreeTableNode(\"WebSockets\");\n" +
                            "      this.au.add((MutableTreeNode)(this.av = new DefaultTreeTableNode(\"Origin\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.aw = new DefaultTreeTableNode(\"Version\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.ax = new DefaultTreeTableNode(\"Protocol\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.ay = new DefaultTreeTableNode(\"Extensions\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.az = new DefaultTreeTableNode(\"Messages Sent\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.aA = new DefaultTreeTableNode(\"Messages Received\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.aB = new DefaultTreeTableNode(\"Control Frames Sent\")));\n" +
                            "      this.au.add((MutableTreeNode)(this.aC = new DefaultTreeTableNode(\"Control Frames Received\")));\n" +
                            "      this.a.add((MutableTreeNode)this.au);\n" +
                            "      DefaultTreeTableNode defaultTreeTableNode1 = new DefaultTreeTableNode(\"Timing\");\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.N = new DefaultTreeTableNode(\"Request Start Time\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.O = new DefaultTreeTableNode(\"Request End Time\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.P = new DefaultTreeTableNode(\"Response Start Time\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.Q = new DefaultTreeTableNode(\"Response End Time\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ad = new DefaultTreeTableNode(\"Duration\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.af = new DefaultTreeTableNode(\"DNS\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ag = new DefaultTreeTableNode(\"Connect\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ah = new DefaultTreeTableNode(\"TLS Handshake\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ai = new DefaultTreeTableNode(\"Request\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.aj = new DefaultTreeTableNode(\"Response\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ae = new DefaultTreeTableNode(\"Latency\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.ak = new DefaultTreeTableNode(\"Speed\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.al = new DefaultTreeTableNode(\"Request Speed\")));\n" +
                            "      defaultTreeTableNode1.add((MutableTreeNode)(this.am = new DefaultTreeTableNode(\"Response Speed\")));\n" +
                            "      this.a.add((MutableTreeNode)defaultTreeTableNode1);\n" +
                            "      DefaultTreeTableNode defaultTreeTableNode2 = new DefaultTreeTableNode(\"Size\");\n" +
                            "      defaultTreeTableNode2.add((MutableTreeNode)(this.R = new DefaultTreeTableNode(\"Request\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.V = new DefaultTreeTableNode(\"TLS Handshake\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.T = new DefaultTreeTableNode(\"Header\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.X = new DefaultTreeTableNode(\"Query String\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.Y = new DefaultTreeTableNode(\"Cookies\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.Z = new DefaultTreeTableNode(\"Body\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.ap = new DefaultTreeTableNode(\"Uncompressed Body\")));\n" +
                            "      this.R.add((MutableTreeNode)(this.an = new DefaultTreeTableNode(\"Compression\")));\n" +
                            "      defaultTreeTableNode2.add((MutableTreeNode)(this.S = new DefaultTreeTableNode(\"Response\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.W = new DefaultTreeTableNode(\"TLS Handshake\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.U = new DefaultTreeTableNode(\"Header\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.aa = new DefaultTreeTableNode(\"Cookies\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.ab = new DefaultTreeTableNode(\"Body\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.aq = new DefaultTreeTableNode(\"Uncompressed Body\")));\n" +
                            "      this.S.add((MutableTreeNode)(this.ao = new DefaultTreeTableNode(\"Compression\")));\n" +
                            "      defaultTreeTableNode2.add((MutableTreeNode)(this.ac = new DefaultTreeTableNode(\"Total\")));\n" +
                            "      this.a.add((MutableTreeNode)defaultTreeTableNode2);\n}");
            //endregion

            overviewClass.getDeclaredMethod(LOAD_METHOD_NAME, new CtClass[]{
                    classPool.get("com.xk72.charles.model.Transaction")
            }).setName(LOAD_METHOD_NAME + "_");

            overviewClass.addMethod(CtNewMethod.make(
                    "private void " + LOAD_METHOD_NAME + "(Transaction var1) {" +
                            "if(var1.getClientProposedSslProtocol() != null) { " +
                            "String ja3Plain = com.xk72.enhancedcharles.JA3Utils.translate(var1); " +
                            "ja3PlainNode.setValue(ja3Plain); " +
                            "String ja3Hash = com.xk72.enhancedcharles.JA3Utils.hash(ja3Plain).toLowerCase();"+
                            "ja3HashNode.setValue(ja3Hash); " +
                            "ja3IdNode.setValue(com.xk72.enhancedcharles.JA3Utils.identify(ja3Hash));}" +
                            LOAD_METHOD_NAME + "_(var1);}",
                    overviewClass));

            transformedClasses.add(new TransformedClass(OVERVIEW_CLASS_PATH, overviewClass.toBytecode()));

            //endregion

            return transformedClasses;

        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }

    }

}
