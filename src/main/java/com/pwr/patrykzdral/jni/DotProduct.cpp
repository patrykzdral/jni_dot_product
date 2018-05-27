#include <jni.h>
#include <iostream>
#include "DotProduct.h"
using namespace std;



JNIEXPORT void JNICALL Java_DotProduct_multi03(JNIEnv *env, jobject thisObj) {
    jclass dotProductClass = env->FindClass("DotProduct");
    int number_of_elements;
    double vectorElement;

    cout << "Podaj liczbę elementów w wektorze: " << endl;
    cin >> number_of_elements;


    jdoubleArray vectorA, vectorB;
    vectorA = env->NewDoubleArray(number_of_elements);
    vectorB = env->NewDoubleArray(number_of_elements);

    jdouble fillVectorA[number_of_elements];
    for (int i = 0; i < number_of_elements; i++) {
        cout << "Podaj " << i + 1 << ". element wektora A: " << endl;
        cin >> vectorElement;

        fillVectorA[i] = vectorElement;
    }

    env->SetDoubleArrayRegion(vectorA, 0, number_of_elements, fillVectorA);

    jmethodID method_a;
    method_a = env->GetMethodID(dotProductClass, "setA", "([D)V");
    env->CallVoidMethod(thisObj, method_a, vectorA);

    jdouble fillVectorB[number_of_elements];
    for (int i = 0; i < number_of_elements; i++) {
        cout << "Podaj " << i + 1 << ". element wektora B: " << endl;
        cin >> vectorElement;

        fillVectorB[i] = vectorElement;
    }

    env->SetDoubleArrayRegion(vectorB, 0, number_of_elements, fillVectorB);
    jmethodID method_b;
    method_b = env->GetMethodID(dotProductClass, "setB", "([D)V");
    env->CallVoidMethod(thisObj, method_b, vectorB);

    env->DeleteLocalRef(vectorA);
    env->DeleteLocalRef(vectorB);

   return;
}

JNIEXPORT jdouble JNICALL Java_DotProduct_multi01(JNIEnv * env, jobject thisObj,jdoubleArray vectorA,jdoubleArray vectorB) {
    jclass dotProductClass = env->FindClass("DotProduct");
    jsize size = env->GetArrayLength( vectorA );

    jdouble *inCArrayA = env->GetDoubleArrayElements(vectorA,NULL);
    jdouble *inCArrayB = env->GetDoubleArrayElements(vectorB,NULL);
    jdouble sum = 0;
    for (int i = 0; i < size; i++)
    {
        sum += inCArrayA[i] * inCArrayB[i];
    }
    jmethodID method_c;
    method_c = env->GetMethodID(dotProductClass, "setC", "(D)V");
    env->CallVoidMethod(thisObj, method_c, sum);

    env->DeleteLocalRef(vectorA);
    env->DeleteLocalRef(vectorB);

   return sum;
}


JNIEXPORT jdouble JNICALL Java_DotProduct_multi02(JNIEnv * env, jobject thisObj,jdoubleArray vectorA) {
    jclass dotProductClass = env->FindClass("DotProduct");
    jsize size = env->GetArrayLength( vectorA );

    jdouble *inCArrayA = env->GetDoubleArrayElements(vectorA,NULL);
    jmethodID get_method;
    get_method = env->GetMethodID(dotProductClass, "getB", "()[D");
    jdoubleArray vectorB =  (jdoubleArray) env->CallObjectMethod(thisObj, get_method);
    jdouble *inCArrayB = env->GetDoubleArrayElements(vectorB,NULL);
    jdouble sum = 0;
    for (int i = 0; i < size; i++)
    {
        sum += inCArrayA[i] * inCArrayB[i];
    }
    jmethodID method_c;
    method_c = env->GetMethodID(dotProductClass, "setC", "(D)V");
    env->CallVoidMethod(thisObj, method_c, sum);
    env->DeleteLocalRef(vectorA);
    env->DeleteLocalRef(vectorB);
   return sum;
}