package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JnzInstruction implements Instruction {

    @Override
    public int getOpCode() {
        return 3;
    }

    @Override
    public void execute(int literalOperand, ProgramState state) {
        Registry registry = state.getRegistry();
        if (registry.getA() == 0) {
            return;
        }
        Pointer pointer = state.getPointer();
        pointer.jump(literalOperand);
    }
}
