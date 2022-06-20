package priv.muchia.practice.ui.demo;

/**
 * FileName: Singleton
 * Author: MuChia
 * Date: 2022/6/20 16:37
 * Description:
 */
public class Singleton {

    /** 双重检验锁 单例模式 */
    //静态方法只能调用静态属性
    //由于指令重排优化，有可能先实例化再初始化，只实例化了还未初始化就切换了线程，这时instance不为空，这样其他线程
    //就会拿到一个未完成初始化的实例，所以要加上volatile关键字禁止指令重排
    private static volatile Singleton instance;

    //因为构造方法私有，所以getInstance 方法只能静态
    public static Singleton getInstance() {
        if (instance == null) {
            //最开始还没实例化之前 可能有多个线程走过这个判断
            synchronized (Singleton.class) {
                //虽然只有第一个线程拿到锁，但是当第一个线程执行完成，释放了锁，其他已经走过判空的线程还是会
                // 继续实例化，所以要在加一层判空，这样只有第一个线程才会实例化
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    /** 静态内部类 单例模式 */
    /*private static class SingletonInstance{
        static final Singleton instance = new Singleton();
    }

    static Singleton getInstance(){
        return SingletonInstance.instance;
    }*/

    /** 枚举类 单例模式 */
    /*private enum SingletonEnum {
        INSTANCE;

        private final Singleton instance = new Singleton();

    }

    public static Singleton getInstance() {
        return SingletonEnum.INSTANCE.instance;
    }*/

    private Singleton() {
    }

    public void sayHello() {

    }
}
