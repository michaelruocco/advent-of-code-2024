package uk.co.mruoc.day17;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JnzInstruction implements Instruction {

    @Override
    public int getOpCode() {
        return 3;
    }

    @Override
    public ProgramState execute(int literalOperand, ProgramState state) {
        if (state.getA() == 0) {
            return state;
        }
        return state.jump(literalOperand);
    }
}
