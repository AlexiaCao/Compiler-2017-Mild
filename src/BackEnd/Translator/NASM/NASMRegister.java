package BackEnd.Translator.NASM;

import BackEnd.Allocator.PhysicalRegister;

public class NASMRegister extends PhysicalRegister {
	public static PhysicalRegister rax = new NASMRegister(1, "rax", false);
	public static PhysicalRegister rcx = new NASMRegister(2, "rcx", false);
	public static PhysicalRegister rdx = new NASMRegister(3, "rdx", false);
	public static PhysicalRegister rbx = new NASMRegister(4, "rbx", true);
	public static PhysicalRegister rsp = new NASMRegister(5, "rsp", true);
	public static PhysicalRegister rbp = new NASMRegister(6, "rbp", true);
	public static PhysicalRegister rsi = new NASMRegister(7, "rsi", false);
	public static PhysicalRegister rdi = new NASMRegister(8, "rdi", false);
	public static PhysicalRegister r8 = new NASMRegister(9, "r8", false);
	public static PhysicalRegister r9 = new NASMRegister(10, "r9", false);
	public static PhysicalRegister r10 = new NASMRegister(11, "r10", false);
	public static PhysicalRegister r11 = new NASMRegister(12, "r11", false);
	public static PhysicalRegister r12 = new NASMRegister(13, "r12", true);
	public static PhysicalRegister r13 = new NASMRegister(14, "r13", true);
	public static PhysicalRegister r14 = new NASMRegister(15, "r14", true);
	public static PhysicalRegister r15 = new NASMRegister(16, "r15", true);


	private NASMRegister(int identity, String name, boolean callPreserved) {
		super(identity, name, callPreserved);
	}

	public static int size() {
		return 8;
	}
}
