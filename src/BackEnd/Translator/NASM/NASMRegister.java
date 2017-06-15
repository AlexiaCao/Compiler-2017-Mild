package BackEnd.Translator.NASM;

import BackEnd.Allocator.PhysicalRegister;

public class NASMRegister extends PhysicalRegister {
	public static PhysicalRegister rax = new NASMRegister(1, "RAX", false);
	public static PhysicalRegister rcx = new NASMRegister(2, "RCX", false);
	public static PhysicalRegister rdx = new NASMRegister(3, "RDX", false);
	public static PhysicalRegister rbx = new NASMRegister(4, "RBX", true);
	public static PhysicalRegister rsp = new NASMRegister(5, "RSP", true);
	public static PhysicalRegister rbp = new NASMRegister(6, "RBP", true);
	public static PhysicalRegister rsi = new NASMRegister(7, "RSI", false);
	public static PhysicalRegister rdi = new NASMRegister(8, "RDI", false);
	public static PhysicalRegister r8 = new NASMRegister(9, "R8", false);
	public static PhysicalRegister r9 = new NASMRegister(10, "R9", false);
	public static PhysicalRegister r10 = new NASMRegister(11, "R10", false);
	public static PhysicalRegister r11 = new NASMRegister(12, "R11", false);
	public static PhysicalRegister r12 = new NASMRegister(13, "R12", true);
	public static PhysicalRegister r13 = new NASMRegister(14, "R13", true);
	public static PhysicalRegister r14 = new NASMRegister(15, "R14", true);
	public static PhysicalRegister r15 = new NASMRegister(16, "R15", true);


	private NASMRegister(int identity, String name, boolean callPreserved) {
		super(identity, name, callPreserved);
	}

	public static int size() {
		return 8;
	}
}
